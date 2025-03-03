package com.pro.reacrtive_example.sec09.assinments;

import com.github.javafaker.Faker;
import com.pro.reacrtive_example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

record  PerchaseRecord(String item,String category,Integer price){}
public class GroupByAssignment {
    private static  final Map<String,UnaryOperator<Flux<PerchaseRecord>>> processor_map= Map.of(
            "Kids",kidsProcess(),
            "Automotive",automotiveProccessor()
    );
    private static final Logger log= LoggerFactory.getLogger(GroupByAssignment.class);
    public static void main(String[] args) {
       getOrderStream()
               .filter(canProcess())
               .groupBy(PerchaseRecord::category)

               .flatMap(x->x.transform(getProcessor(x.key())))
               .subscribe(Util.subscriber());
      Util.sleepSeconds(60);
    }
    private static  Mono<PerchaseRecord> getFreeorder(PerchaseRecord perchaseRecord){

        return  Mono.fromSupplier(()->new PerchaseRecord(perchaseRecord.item()+"-free",perchaseRecord.category(),0));

    }
    private static UnaryOperator<Flux<PerchaseRecord>> automotiveProccessor(){
        return  flux->flux.map(x->new PerchaseRecord(x.item(),x.category(),x.price()+100));
    }
    private  static  UnaryOperator<Flux<PerchaseRecord>> kidsProcess(){
        return  flux->flux.flatMap(x->getFreeorder(x).flux().startWith(x));

    }

    public static Predicate<PerchaseRecord> canProcess(){
        return po-> processor_map.containsKey(po.category());
    }
    public  static  UnaryOperator<Flux<PerchaseRecord>> getProcessor(String category){
        return processor_map.get(category);
    }
    private static Mono<Void> processOrder(GroupedFlux<String,PerchaseRecord> gp){
        return gp
                .doOnNext(x->log.info(" key : {} value: ",gp.key(),x))
                .then();
    }
    public  static Flux<PerchaseRecord> getOrderStream(){
        return  Flux.interval(Duration.ofMillis(500))
                .map(x->new PerchaseRecord(Util.faker.commerce().productName(),Util.faker.commerce().department(), Util.faker.random().nextInt(100,1200)));

    }
}

