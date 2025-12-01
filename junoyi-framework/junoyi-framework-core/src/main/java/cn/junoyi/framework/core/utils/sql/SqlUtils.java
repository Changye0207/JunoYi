package cn.junoyi.framework.core.utils.sql;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * sql 操作工具类
 *
 * @author Fan
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlUtils {

    public static final String SQL_REGEX = "select |insert |delete |update |drop |count |exec |chr |mid |master |truncate |char |and |declare ";
}