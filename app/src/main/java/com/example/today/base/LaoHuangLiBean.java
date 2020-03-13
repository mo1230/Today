package com.example.today.base;

public class LaoHuangLiBean {
    /**
     * reason : successed
     * result : {"id":"3626","yangli":"2020-03-03","yinli":"庚子(鼠)年二月初十","wuxing":"复灯火 平执位","chongsha":"冲猪(己亥)煞东","baiji":"乙不栽植千株不长 已不远行财物伏藏","jishen":"相日 宝光","yi":"作灶 解除 平治道涂","xiongshen":"死神 月刑 月害 游祸 五虚 重日","ji":"栽种 出行 祈福 行丧 纳畜 安葬 安门 伐木 作梁 牧养"}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * id : 3626
         * yangli : 2020-03-03
         * yinli : 庚子(鼠)年二月初十
         * wuxing : 复灯火 平执位
         * chongsha : 冲猪(己亥)煞东
         * baiji : 乙不栽植千株不长 已不远行财物伏藏
         * jishen : 相日 宝光
         * yi : 作灶 解除 平治道涂
         * xiongshen : 死神 月刑 月害 游祸 五虚 重日
         * ji : 栽种 出行 祈福 行丧 纳畜 安葬 安门 伐木 作梁 牧养
         */

        private String id;
        private String yangli;
        private String yinli;
        private String wuxing;
        private String chongsha;
        private String baiji;
        private String jishen;
        private String yi;
        private String xiongshen;
        private String ji;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYangli() {
            return yangli;
        }

        public void setYangli(String yangli) {
            this.yangli = yangli;
        }

        public String getYinli() {
            return yinli;
        }

        public void setYinli(String yinli) {
            this.yinli = yinli;
        }

        public String getWuxing() {
            return wuxing;
        }

        public void setWuxing(String wuxing) {
            this.wuxing = wuxing;
        }

        public String getChongsha() {
            return chongsha;
        }

        public void setChongsha(String chongsha) {
            this.chongsha = chongsha;
        }

        public String getBaiji() {
            return baiji;
        }

        public void setBaiji(String baiji) {
            this.baiji = baiji;
        }

        public String getJishen() {
            return jishen;
        }

        public void setJishen(String jishen) {
            this.jishen = jishen;
        }

        public String getYi() {
            return yi;
        }

        public void setYi(String yi) {
            this.yi = yi;
        }

        public String getXiongshen() {
            return xiongshen;
        }

        public void setXiongshen(String xiongshen) {
            this.xiongshen = xiongshen;
        }

        public String getJi() {
            return ji;
        }

        public void setJi(String ji) {
            this.ji = ji;
        }
    }
}
