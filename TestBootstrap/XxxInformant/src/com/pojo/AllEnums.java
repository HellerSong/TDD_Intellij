package com.pojo;

/**
 * Created by Administrator on 6/22/2016.
 */
public class AllEnums {
    public enum ClueStatus {
        Delete(-1, "已删除"), All(0, "全部"), Pending(1, "待处理"), Finish(2, "已处理");

        private int _value;
        private String _name;

        ClueStatus(int value, String name) {
            _value = value;
            _name = name;
        }

        public int value() {
            return _value;
        }

        public String getName() {
            return _name;
        }
    }
}
