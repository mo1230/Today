package com.example.today.base;

public class TodaysUrl {
    // 获取历史上的今天对应的网址
    public static String getTodayUrl(String version, int month, int day){
        String url = "http://api.juheapi.com/japi/toh?v=" + version + "&month=" + month + "&day=" +day+ "&key=d4f88eb41014972c81635af679d6bcdd";
        return url;
    }

    // 获取老黄历对应的网址
    public static String getLaoHuangLiUrl(String day){
        String url = "http://v.juhe.cn/laohuangli/d?" + "date="+ day +"&key=fa6cee0967c018d0aa5b570e4d5f2b96";
        return url;
    }

    // 根据id查询历史上的今天对应的网址
    public static String getTodayUrlById(String version, String id){
        String url = "http://api.juheapi.com/japi/tohdet?v=" + version + "&id=" + id + "&key=d4f88eb41014972c81635af679d6bcdd";
        return url;
    }
}
