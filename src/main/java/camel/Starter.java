
package camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public final class Starter {

    public static void main(String[] args) throws Exception {

        //Контекст Camel представляет собой исполнительное окружение для запуска маршрутов
        try (CamelContext camel = new DefaultCamelContext()) {
            //создается маршрут, определяющий что  сделать при получении события таймера
            camel.addRoutes(createBasicRoute());
            camel.start();
            //позволяет наблюдать вывод сообщения  в течение 4 сек перед остановкой приложения
            Thread.sleep(4_000);
            camel.stop();
        }
    }

    static RouteBuilder createBasicRoute() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                //таймер генерирует сообщения с определенным интервалом времени, и эти сообщения поступают на обработку в маршруте
                from("timer:foo")
                        .log("Hello Camel");
            }
        };
    }
}
