# JunoYi 权限系统设计文档

## 一、设计理念

### 1.1 核心思想

打破传统 RBAC 框架（RuoYi/Sa-Token/Shiro）的局限性，采用 **权限节点 + 权限组** 的极简设计模式，灵感来源于 Minecraft 服务器的 LuckPerms 权限插件。

**核心原则：权限节点就是字符串，不需要预先注册到数据库！**

### 1.2 与传统框架对比

| 问题 | 传统框架 | JunoYi 方案 |
|------|----------|-------------|
| 权限与菜单耦合 | 权限必须绑定菜单 | 权限节点独立，与菜单解耦 |
| 权限注册 | 必须预先在数据库注册 | **无需注册，直接使用字符串** |
| API 权限 | 每个 API 必须对应一个菜单 | 权限节点可独立定义 |
| 通配符支持 | 不支持 | 支持 `system.user.*` |
| 黑名单 | 不支持 | 支持 `-system.user.delete` |
| 数据范围 | 硬编码或简单配置 | 动态策略引擎 |

---

## 二、核心概念

### 2.1 权限节点（Permission Node）

权限节点是权限系统的最小单元，**就是一个字符串**，采用点分隔的层级结构：

```
system.user.create      # 创建用户
system.user.delete      # 删除用户
system.user.*           # 用户模块所有权限（通配符）
system.*                # 系统模块所有权限
*                       # 超级管理员（所有权限）
-system.user.delete     # 黑名单：禁止删除用户
```

### 2.2 权限组（Permission Group）

权限组直接存储权限字符串数组（JSON格式），**不需要关联权限表**：

```json
{
  "group_code": "user_manager",
  "group_name": "用户管理员",
  "permissions": [
    "system.user.*",
    "system.role.view",
    "-system.user.delete"
  ]
}
```

### 2.3 授权模型

```
用户 → 权限组 → 权限字符串数组
```

权限组可以关联：
- 用户（User）
- 角色（Role）
- 部门（Department）

---

## 三、极简数据模型

### 3.1 核心表结构（仅3张表）

#### sys_perm_group（权限组表）

```sql
CREATE TABLE sys_perm_group (
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    group_code      VARCHAR(50) NOT NULL UNIQUE,   -- 权限组编码
    group_name      VARCHAR(100) NOT NULL,         -- 权限组名称
    permissions     TEXT,                          -- 权限字符串数组（JSON）
    parent_id       BIGINT DEFAULT 0,              -- 父权限组（支持继承）
    priority        INT DEFAULT 0,                 -- 优先级（数值越大优先级越高）
    description     VARCHAR(500),
    status          TINYINT DEFAULT 1,
    create_time     DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 示例数据
INSERT INTO sys_perm_group (group_code, group_name, permissions, priority) VALUES
('super_admin', '超级管理员', '["*"]', 100),
('user_manager', '用户管理员', '["system.user.*", "system.role.view", "-system.user.delete"]', 50),
('guest', '访客', '["system.user.view", "system.dashboard.view"]', 0);
```

#### sys_user_group（用户-权限组关联表）

```sql
CREATE TABLE sys_user_group (
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id         BIGINT NOT NULL,
    group_id        BIGINT NOT NULL,
    expire_time     DATETIME,                      -- 过期时间（支持临时授权）
    create_time     DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_user_group (user_id, group_id)
);
```

#### sys_role_group（角色-权限组关联表，可选）

```sql
CREATE TABLE sys_role_group (
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_id         BIGINT NOT NULL,
    group_id        BIGINT NOT NULL,
    create_time     DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_role_group (role_id, group_id)
);
```

---

## 四、权限匹配引擎

### 4.1 匹配规则

```
1. 精确匹配：system.user.delete
2. 单级通配：system.user.* 匹配 system.user.delete
3. 多级通配：system.** 匹配 system.user.delete.field
4. 全局通配：* 匹配所有
5. 黑名单：-system.user.delete 禁止该权限（优先级最高）
```

### 4.2 匹配优先级

```
黑名单（-开头） > 精确匹配 > 通配符匹配
```

### 4.3 权限计算流程

```
用户登录
    ↓
获取用户关联的所有权限组
    ↓
合并所有权限组的 permissions 字段
    ↓
处理权限继承（父权限组）
    ↓
缓存到 Redis（Set<String>）
    ↓
请求时纯字符串匹配
```

---

## 五、使用示例

### 5.1 后端使用

```java
// 1. 注解方式
@Permission("system.user.delete")
@DeleteMapping("/{id}")
public R<?> deleteUser(@PathVariable Long id) {
    // ...
}

// 2. 多权限（OR 关系）
@Permission(value = {"system.user.view", "system.admin"}, logical = Logical.OR)
public R<?> getUser() { }

// 3. 编程方式
if (PermissionHelper.hasPermission("system.user.delete")) {
    // 有权限
}

// 4. 数据范围
@DataScope(scopeType = DataScopeType.DEPT)
public List<User> listUsers() {
    // SQL 自动追加数据范围条件
}
```

### 5.2 前端使用

```javascript
// 登录后获取用户权限列表
const permissions = ['system.user.*', 'system.role.view', '-system.user.delete']

// 判断是否有权限
function hasPermission(permission) {
    // 1. 检查黑名单
    if (permissions.includes('-' + permission)) return false
    // 2. 精确匹配
    if (permissions.includes(permission)) return true
    // 3. 通配符匹配
    return permissions.some(p => matchWildcard(p, permission))
}
```

---

## 六、配置项

```yaml
junoyi:
  permission:
    enable: true
    cache:
      enable: true
      expire: 3600          # 缓存过期时间（秒）
    super-admin:
      enable: true
      user-ids: [1]         # 超级管理员用户ID
```

---

## 七、总结

JunoYi 权限系统的核心优势：

1. **极简设计** - 仅3张表，无需权限注册表
2. **权限即字符串** - 直接在代码中使用，无需预先配置
3. **通配符支持** - `system.user.*` 一次授权多个操作
4. **黑名单支持** - `-system.user.delete` 精确禁止
5. **高性能** - Redis 缓存 + 纯字符串匹配
6. **动态权限** - 修改即生效，无需重启
