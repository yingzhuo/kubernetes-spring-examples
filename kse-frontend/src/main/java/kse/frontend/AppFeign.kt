package kse.frontend

import com.github.yingzhuo.carnival.openfeign.EnableFeignClients
import feign.Contract
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients
open class AppFeign {

    @Bean
    open fun contract(): Contract = Contract.Default()

}
