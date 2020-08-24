package tk.mybatis.simple.model;

public enum Enabled {
    /**
     * 禁用
     */
    disabled(1),
    /**
     * 启用
     */
    enabled(0);

    private final int value;

    private Enabled(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
