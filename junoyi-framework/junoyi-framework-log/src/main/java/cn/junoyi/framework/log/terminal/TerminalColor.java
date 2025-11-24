package cn.junoyi.framework.log.terminal;

/**
 * 终端字体颜色
 *
 * @author Fan
 */
public class TerminalColor {
    public static final String RESET = "\033[0m";

    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";

    // Bold versions
    public static final String BOLD_WHITE = "\033[1;37m";
    public static final String BOLD_GREEN = "\033[1;32m";
    public static final String BOLD_CYAN = "\033[1;36m";
}