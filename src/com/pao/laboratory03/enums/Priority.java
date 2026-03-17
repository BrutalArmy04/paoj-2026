package com.pao.laboratory03.enums;

public class Priority {
    
    public enum PriorityEnum {
        LOW(1, "green") {
            @Override
            public String getEmoji() { return "🟢"; }
        },
        MEDIUM(2, "yellow") {
            @Override
            public String getEmoji() { return "🟡"; }
        },
        HIGH(3, "orange") {
            @Override
            public String getEmoji() { return "🟠"; }
        },
        CRITICAL(4, "red") {
            @Override
            public String getEmoji() { return "🔴"; }
        };
    
        private final int level;
        private final String color;

        PriorityEnum(int level, String color) {
            this.level = level;
            this.color = color;
        }

        public String getColor() { return color; }
        public int getLevel() { return level; }

        public abstract String getEmoji();
    }
}