package com.boutique.shipping.messaging;
import org.springframework.amqp.core.*;import org.springframework.context.annotation.*;
@Configuration public class ShippingRabbitQueueConfig{
 @Bean Queue shippingOrderQueue(){return QueueBuilder.durable("boutique.shipping.order-confirmed").deadLetterExchange("boutique.dlx").deadLetterRoutingKey("shipping.order-confirmed").build();}
 @Bean Queue shippingOrderDlq(){return QueueBuilder.durable("boutique.shipping.order-confirmed.dlq").build();}
 @Bean TopicExchange boutiqueDeadLetterExchange(){return new TopicExchange("boutique.dlx",true,false);}
 @Bean Binding shippingOrderBinding(Queue shippingOrderQueue,TopicExchange boutiqueEventsExchange){return BindingBuilder.bind(shippingOrderQueue).to(boutiqueEventsExchange).with("order.order_confirmed");}
 @Bean Binding shippingDlqBinding(Queue shippingOrderDlq,TopicExchange boutiqueDeadLetterExchange){return BindingBuilder.bind(shippingOrderDlq).to(boutiqueDeadLetterExchange).with("shipping.order-confirmed");}
}
