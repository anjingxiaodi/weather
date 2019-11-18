package com.ok100.weather.bean;

/**
 * @Description: This is WeatherTotalBean
 * @Author: QianDongDong
 * @Time: 2019/11/15 10:56
 * @Email: qdd2977@sina.com
 * @org: www.vanlian.cn 万科链家（北京）装饰有限公司
 */
public class WeatherTotalBean {

    /**
     * ret : 200
     * data : {"cityinfo":{"provinces":"浙江","city":"杭州","area":"西湖","id":"101210113","prov_py":"zhejiang","city_py":"hangzhou","qh":"0571","jb":"3","yb":"310000","area_py":"xihu","area_short_code":"xh","lng":"120.12979","lat":"30.25949"},"now":{"id":"101210113","area_name":"西湖","city":{"night_air_temperature":"24","day_air_temperature":"31","wind_direction":"东风","wind_power":"4-5级转3-4级","weather":"多云","weather_code":"01"},"detail":{"time":"14:55","date":"09月12日","week":"四","temperature":"33","wind_direction":"东北风","wind_direction_str":"NE","wind_power":"2级","wind_speed":"<12km/h","humidity":"54%","weather":"多云","weather_code":"01","weather_english":"Cloudy","qy":"1006","njd":"10.98km","rain":"0.0","aqi":"57","quality":"良","aqi_pm25":"","so2":"2","o3":"16","co":"8","no2":"25","pm10":"73","nongli":"八月十四","sun_begin":"05:42","sun_end":"18:09"},"update_time":1568273100}}
     * qt : 0.065
     */

    private int ret;
    private DataBean data;
    private double qt;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public double getQt() {
        return qt;
    }

    public void setQt(double qt) {
        this.qt = qt;
    }

    public static class DataBean {
        /**
         * cityinfo : {"provinces":"浙江","city":"杭州","area":"西湖","id":"101210113","prov_py":"zhejiang","city_py":"hangzhou","qh":"0571","jb":"3","yb":"310000","area_py":"xihu","area_short_code":"xh","lng":"120.12979","lat":"30.25949"}
         * now : {"id":"101210113","area_name":"西湖","city":{"night_air_temperature":"24","day_air_temperature":"31","wind_direction":"东风","wind_power":"4-5级转3-4级","weather":"多云","weather_code":"01"},"detail":{"time":"14:55","date":"09月12日","week":"四","temperature":"33","wind_direction":"东北风","wind_direction_str":"NE","wind_power":"2级","wind_speed":"<12km/h","humidity":"54%","weather":"多云","weather_code":"01","weather_english":"Cloudy","qy":"1006","njd":"10.98km","rain":"0.0","aqi":"57","quality":"良","aqi_pm25":"","so2":"2","o3":"16","co":"8","no2":"25","pm10":"73","nongli":"八月十四","sun_begin":"05:42","sun_end":"18:09"},"update_time":1568273100}
         */

        private CityinfoBean cityinfo;
        private NowBean now;

        public CityinfoBean getCityinfo() {
            return cityinfo;
        }

        public void setCityinfo(CityinfoBean cityinfo) {
            this.cityinfo = cityinfo;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public static class CityinfoBean {
            /**
             * provinces : 浙江
             * city : 杭州
             * area : 西湖
             * id : 101210113
             * prov_py : zhejiang
             * city_py : hangzhou
             * qh : 0571
             * jb : 3
             * yb : 310000
             * area_py : xihu
             * area_short_code : xh
             * lng : 120.12979
             * lat : 30.25949
             */

            private String provinces;
            private String city;
            private String area;
            private String id;
            private String prov_py;
            private String city_py;
            private String qh;
            private String jb;
            private String yb;
            private String area_py;
            private String area_short_code;
            private String lng;
            private String lat;

            public String getProvinces() {
                return provinces;
            }

            public void setProvinces(String provinces) {
                this.provinces = provinces;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProv_py() {
                return prov_py;
            }

            public void setProv_py(String prov_py) {
                this.prov_py = prov_py;
            }

            public String getCity_py() {
                return city_py;
            }

            public void setCity_py(String city_py) {
                this.city_py = city_py;
            }

            public String getQh() {
                return qh;
            }

            public void setQh(String qh) {
                this.qh = qh;
            }

            public String getJb() {
                return jb;
            }

            public void setJb(String jb) {
                this.jb = jb;
            }

            public String getYb() {
                return yb;
            }

            public void setYb(String yb) {
                this.yb = yb;
            }

            public String getArea_py() {
                return area_py;
            }

            public void setArea_py(String area_py) {
                this.area_py = area_py;
            }

            public String getArea_short_code() {
                return area_short_code;
            }

            public void setArea_short_code(String area_short_code) {
                this.area_short_code = area_short_code;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            @Override
            public String toString() {
                return "CityinfoBean{" +
                        "provinces='" + provinces + '\'' +
                        ", city='" + city + '\'' +
                        ", area='" + area + '\'' +
                        ", id='" + id + '\'' +
                        ", prov_py='" + prov_py + '\'' +
                        ", city_py='" + city_py + '\'' +
                        ", qh='" + qh + '\'' +
                        ", jb='" + jb + '\'' +
                        ", yb='" + yb + '\'' +
                        ", area_py='" + area_py + '\'' +
                        ", area_short_code='" + area_short_code + '\'' +
                        ", lng='" + lng + '\'' +
                        ", lat='" + lat + '\'' +
                        '}';
            }
        }

        public static class NowBean {
            /**
             * id : 101210113
             * area_name : 西湖
             * city : {"night_air_temperature":"24","day_air_temperature":"31","wind_direction":"东风","wind_power":"4-5级转3-4级","weather":"多云","weather_code":"01"}
             * detail : {"time":"14:55","date":"09月12日","week":"四","temperature":"33","wind_direction":"东北风","wind_direction_str":"NE","wind_power":"2级","wind_speed":"<12km/h","humidity":"54%","weather":"多云","weather_code":"01","weather_english":"Cloudy","qy":"1006","njd":"10.98km","rain":"0.0","aqi":"57","quality":"良","aqi_pm25":"","so2":"2","o3":"16","co":"8","no2":"25","pm10":"73","nongli":"八月十四","sun_begin":"05:42","sun_end":"18:09"}
             * update_time : 1568273100
             */

            private String id;
            private String area_name;
            private CityBean city;
            private DetailBean detail;
            private int update_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getArea_name() {
                return area_name;
            }

            public void setArea_name(String area_name) {
                this.area_name = area_name;
            }

            public CityBean getCity() {
                return city;
            }

            public void setCity(CityBean city) {
                this.city = city;
            }

            public DetailBean getDetail() {
                return detail;
            }

            public void setDetail(DetailBean detail) {
                this.detail = detail;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public static class CityBean {
                /**
                 * night_air_temperature : 24
                 * day_air_temperature : 31
                 * wind_direction : 东风
                 * wind_power : 4-5级转3-4级
                 * weather : 多云
                 * weather_code : 01
                 */

                private String night_air_temperature;
                private String day_air_temperature;
                private String wind_direction;
                private String wind_power;
                private String weather;
                private String weather_code;

                public String getNight_air_temperature() {
                    return night_air_temperature;
                }

                public void setNight_air_temperature(String night_air_temperature) {
                    this.night_air_temperature = night_air_temperature;
                }

                public String getDay_air_temperature() {
                    return day_air_temperature;
                }

                public void setDay_air_temperature(String day_air_temperature) {
                    this.day_air_temperature = day_air_temperature;
                }

                public String getWind_direction() {
                    return wind_direction;
                }

                public void setWind_direction(String wind_direction) {
                    this.wind_direction = wind_direction;
                }

                public String getWind_power() {
                    return wind_power;
                }

                public void setWind_power(String wind_power) {
                    this.wind_power = wind_power;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public String getWeather_code() {
                    return weather_code;
                }

                public void setWeather_code(String weather_code) {
                    this.weather_code = weather_code;
                }

                @Override
                public String toString() {
                    return "CityBean{" +
                            "night_air_temperature='" + night_air_temperature + '\'' +
                            ", day_air_temperature='" + day_air_temperature + '\'' +
                            ", wind_direction='" + wind_direction + '\'' +
                            ", wind_power='" + wind_power + '\'' +
                            ", weather='" + weather + '\'' +
                            ", weather_code='" + weather_code + '\'' +
                            '}';
                }
            }

            public static class DetailBean {
                /**
                 * time : 14:55
                 * date : 09月12日
                 * week : 四
                 * temperature : 33
                 * wind_direction : 东北风
                 * wind_direction_str : NE
                 * wind_power : 2级
                 * wind_speed : <12km/h
                 * humidity : 54%
                 * weather : 多云
                 * weather_code : 01
                 * weather_english : Cloudy
                 * qy : 1006
                 * njd : 10.98km
                 * rain : 0.0
                 * aqi : 57
                 * quality : 良
                 * aqi_pm25 :
                 * so2 : 2
                 * o3 : 16
                 * co : 8
                 * no2 : 25
                 * pm10 : 73
                 * nongli : 八月十四
                 * sun_begin : 05:42
                 * sun_end : 18:09
                 */

                private String time;
                private String date;
                private String week;
                private String temperature;
                private String wind_direction;
                private String wind_direction_str;
                private String wind_power;
                private String wind_speed;
                private String humidity;
                private String weather;
                private String weather_code;
                private String weather_english;
                private String qy;
                private String njd;
                private String rain;
                private String aqi;
                private String quality;
                private String aqi_pm25;
                private String so2;
                private String o3;
                private String co;
                private String no2;
                private String pm10;
                private String nongli;
                private String sun_begin;
                private String sun_end;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWind_direction() {
                    return wind_direction;
                }

                public void setWind_direction(String wind_direction) {
                    this.wind_direction = wind_direction;
                }

                public String getWind_direction_str() {
                    return wind_direction_str;
                }

                public void setWind_direction_str(String wind_direction_str) {
                    this.wind_direction_str = wind_direction_str;
                }

                public String getWind_power() {
                    return wind_power;
                }

                public void setWind_power(String wind_power) {
                    this.wind_power = wind_power;
                }

                public String getWind_speed() {
                    return wind_speed;
                }

                public void setWind_speed(String wind_speed) {
                    this.wind_speed = wind_speed;
                }

                public String getHumidity() {
                    return humidity;
                }

                public void setHumidity(String humidity) {
                    this.humidity = humidity;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public String getWeather_code() {
                    return weather_code;
                }

                public void setWeather_code(String weather_code) {
                    this.weather_code = weather_code;
                }

                public String getWeather_english() {
                    return weather_english;
                }

                public void setWeather_english(String weather_english) {
                    this.weather_english = weather_english;
                }

                public String getQy() {
                    return qy;
                }

                public void setQy(String qy) {
                    this.qy = qy;
                }

                public String getNjd() {
                    return njd;
                }

                public void setNjd(String njd) {
                    this.njd = njd;
                }

                public String getRain() {
                    return rain;
                }

                public void setRain(String rain) {
                    this.rain = rain;
                }

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getQuality() {
                    return quality;
                }

                public void setQuality(String quality) {
                    this.quality = quality;
                }

                public String getAqi_pm25() {
                    return aqi_pm25;
                }

                public void setAqi_pm25(String aqi_pm25) {
                    this.aqi_pm25 = aqi_pm25;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getNongli() {
                    return nongli;
                }

                public void setNongli(String nongli) {
                    this.nongli = nongli;
                }

                public String getSun_begin() {
                    return sun_begin;
                }

                public void setSun_begin(String sun_begin) {
                    this.sun_begin = sun_begin;
                }

                public String getSun_end() {
                    return sun_end;
                }

                public void setSun_end(String sun_end) {
                    this.sun_end = sun_end;
                }

                @Override
                public String toString() {
                    return "DetailBean{" +
                            "time='" + time + '\'' +
                            ", date='" + date + '\'' +
                            ", week='" + week + '\'' +
                            ", temperature='" + temperature + '\'' +
                            ", wind_direction='" + wind_direction + '\'' +
                            ", wind_direction_str='" + wind_direction_str + '\'' +
                            ", wind_power='" + wind_power + '\'' +
                            ", wind_speed='" + wind_speed + '\'' +
                            ", humidity='" + humidity + '\'' +
                            ", weather='" + weather + '\'' +
                            ", weather_code='" + weather_code + '\'' +
                            ", weather_english='" + weather_english + '\'' +
                            ", qy='" + qy + '\'' +
                            ", njd='" + njd + '\'' +
                            ", rain='" + rain + '\'' +
                            ", aqi='" + aqi + '\'' +
                            ", quality='" + quality + '\'' +
                            ", aqi_pm25='" + aqi_pm25 + '\'' +
                            ", so2='" + so2 + '\'' +
                            ", o3='" + o3 + '\'' +
                            ", co='" + co + '\'' +
                            ", no2='" + no2 + '\'' +
                            ", pm10='" + pm10 + '\'' +
                            ", nongli='" + nongli + '\'' +
                            ", sun_begin='" + sun_begin + '\'' +
                            ", sun_end='" + sun_end + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "NowBean{" +
                        "id='" + id + '\'' +
                        ", area_name='" + area_name + '\'' +
                        ", city=" + city +
                        ", detail=" + detail +
                        ", update_time=" + update_time +
                        '}';
            }

        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "cityinfo=" + cityinfo +
                    ", now=" + now +
                    '}';
        }

    }

    @Override
    public String toString() {
        return "WeatherTotalBean{" +
                "ret=" + ret +
                ", data=" + data +
                ", qt=" + qt +
                '}';
    }
}
