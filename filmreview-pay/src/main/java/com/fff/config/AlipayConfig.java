package com.fff.config;


import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101200671908";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCPz3//c5+0uyxVdBhND4jAH2fhJeJYDOE6Jva+wdzqEX7OjBhO6UhetzHlCz5oJfv4ue8WBqo+eUW6U8sVzjemK2oOjqxkUgIz6TQqZITJm23lTRjy6RRHcpZgUSIOCa5QHmuTURdfHVKfFUhU8BAMXKbQ6UCgdr2J+999A3olZd5LRrg9fANKvqeHEDEbChZ9Yu4u1dYAM/nAPN8+hTkp0BbfCBJfykmA2nli+jrULsHYFPJNMxj7LlexJXTcmkROw09NYFfFkuhj0Y4gfdst1bzGtIzPZ2mmoYS31Y7d4PiMMTKwA7kSLOskqpIQGTa3g/KwWvaaA6r2FDKJA5h7AgMBAAECggEAI0qlu7fRQpYIbDQgXpDoW1WZtYwJftgyUYYMWYXTw/FlMCT7NipTo0LL0MywlPh1XHWssbgTAV/bd71mgkRoLbRLw6TJs5jYh4q8TYb7e0pUbsg7zija3CnHs31VoSJJD8pXVgC5fpMAmp+rb4S2O4JlQbFsNbTOENWp49D9ob19tZm2GgeZ4iNlKAW1p03lvvLikCV4p972tHYeKNNPcSERERvt1ZSGeJpl4bygrPaMZdHmbhY7HMx0vzUN0ukbEBJ0ryPy1p4Op2UFWcP0Na2/8N/dA7IDpfnKta6w75yFR9BTVqHkCZsGdfkems8zAUiELrqXoDtVRfxXiec+wQKBgQDgqvwBghcisQcNh2I46zU+pSl2wtiqyAHhgvVh5AuEph+9fS9JW8re4QIeHf2f9PNPrPubrZFvBHFCjPsnR67JpuLtM5SaVVl74juMIcmSEAa+A17OHUaRXYyH/pe+BYMoX9VJavsqKUFwYYWNfKOprYmR5GLjX5Y9PHz4T0+2mwKBgQCj3cYwpdCTd//o5RFY3nwOD9R2VsU89kWRhf4uFeVJajqSrqHvRePg555w8mYlmVG9lEripj4c935b9pjJEddHw9G6JYBCh+k+UpIxEPDV/dTDabT/HuRB9iEW5nTdRZm6ZJXthTFkRmr2DweX4ITBSchfwhqUFSZFCPH68yDToQKBgQCk3/YmqTCPTfkODZ+gg8REX/LI6/8Tfcobk/6+QxvM0hkS926fWFTOMbRbI8k//QQeIaVYPw0uusTfgRTDY9C6vdVlG+hfRmlwB7uJgDYsEcTQ5ypI+qUSAuQh3U3Arszs8T9WLMN70mDqtzbKeYCmAeumfEOx9zELaXuQwquOJwKBgDOqPc2iyhNk11vftUt2wlhQp2U0UHW7FA4g2BZBswwloRgUVVwyhfmoLW1hD9G0+G+0bLPvRFAvvDhUlLF4nY6z79En97uCTIjo0c0EkVp28Hk4P+fcHo2pg59Lw/br9d8tzZOY1gzOovS3omZBVLn4gGOouGaOEIXdnyXHOEdhAoGBAKLD1UR/HFFDausOZO3k5j5adjGEmMmdZrds0g6BF40KYu927uKXklLdG/R6So6WNuQRWDSNIOmJ6Va93Ur6M1s78KN01Z44S6I2i24XBcqqvm2aQQKDdvUvx2t302WpmZvBCzHj4ADPFA86sULUvMgywnRXTs4JZuaTn2sKmugv";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjydJygLMZBOHwpLJH+VlMaNuiaYyECpJdqKr3H6U9T6b9taSdfDVqHu21ukrkUrgXhhZN/xIxK4SbLlcHcfbkfx+d/wb+Aac2YVkO1MnTgf1iWR25Ho4PoCOC78QV5TGcBzq3bIQzVL/6Gx6uSdnnAPUU3153xYxruswNNsKzmkJxv9bbxK28ibNtG9K/fTEpeoweyTFSEr1lNyo8DKDYdXwSYzeDHSoXgC6nd5Ts8vjR+F5mRTrxa7q9VJjmCuF3PHmSQaTmCXXA9Mh6H3Odh9cAxMeAXoNSflka95SVPlDfG7TLOnfXImj+uon9v+ramg23ZqUvAWSZ6Ugve1nSQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://2g71995y39.wicp.vip/alipay/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8088/#/";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
