package com.example.mobilephoneshop.home.bean;

import java.util.List;


public class TypeListBean {



    private int code;
    private String msg;


    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private boolean catalog_data;
        private boolean brand_data;
        private String is_recommended;
        /**
         * product_id : 4183
         * origin_price : 29.00
         * channel_id : 12
         * brand_id : 77
         * p_catalog_id : 73
         * supplier_type : 1
         * supplier_code : 1101029
         * name : 【Honest首饰】猫爪子戒指 925银 开口指环 萌宠 喵星人耳朵
         * cover_price : 26.00
         * brief :
         * figure : http://f.p.cycangcdn.com/1453345346749.jpg
         * sell_time_start : 1453305600
         * sell_time_end : 1453910400
         */

        private List<PageDataBean> page_data;

        public boolean isCatalog_data() {
            return catalog_data;
        }

        public void setCatalog_data(boolean catalog_data) {
            this.catalog_data = catalog_data;
        }

        public boolean isBrand_data() {
            return brand_data;
        }

        public void setBrand_data(boolean brand_data) {
            this.brand_data = brand_data;
        }

        public String getIs_recommended() {
            return is_recommended;
        }

        public void setIs_recommended(String is_recommended) {
            this.is_recommended = is_recommended;
        }

        public List<PageDataBean> getPage_data() {
            return page_data;
        }

        public void setPage_data(List<PageDataBean> page_data) {
            this.page_data = page_data;
        }

        public static class PageDataBean {
            private String product_id;
            private String origin_price;
            private String channel_id;
            private String brand_id;
            private String p_catalog_id;
            private String supplier_type;
            private String supplier_code;
            private String name;
            private String cover_price;
            private String brief;
            private String figure;
            private String sell_time_start;
            private String sell_time_end;

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getOrigin_price() {
                return origin_price;
            }

            public void setOrigin_price(String origin_price) {
                this.origin_price = origin_price;
            }

            public String getChannel_id() {
                return channel_id;
            }

            public void setChannel_id(String channel_id) {
                this.channel_id = channel_id;
            }

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getP_catalog_id() {
                return p_catalog_id;
            }

            public void setP_catalog_id(String p_catalog_id) {
                this.p_catalog_id = p_catalog_id;
            }

            public String getSupplier_type() {
                return supplier_type;
            }

            public void setSupplier_type(String supplier_type) {
                this.supplier_type = supplier_type;
            }

            public String getSupplier_code() {
                return supplier_code;
            }

            public void setSupplier_code(String supplier_code) {
                this.supplier_code = supplier_code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCover_price() {
                return cover_price;
            }

            public void setCover_price(String cover_price) {
                this.cover_price = cover_price;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getFigure() {
                return figure;
            }

            public void setFigure(String figure) {
                this.figure = figure;
            }

            public String getSell_time_start() {
                return sell_time_start;
            }

            public void setSell_time_start(String sell_time_start) {
                this.sell_time_start = sell_time_start;
            }

            public String getSell_time_end() {
                return sell_time_end;
            }

            public void setSell_time_end(String sell_time_end) {
                this.sell_time_end = sell_time_end;
            }
        }
    }
}
