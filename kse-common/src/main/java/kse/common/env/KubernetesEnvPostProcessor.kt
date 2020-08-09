package kse.common.env

import com.github.yingzhuo.springboot.env.postprocessor.AbstractConventionEnvironmentPostProcessor
import org.springframework.boot.SpringApplication
import org.springframework.core.env.ConfigurableEnvironment

class KubernetesEnvPostProcessor : AbstractConventionEnvironmentPostProcessor() {

    override fun getName(environment: ConfigurableEnvironment, application: SpringApplication): String = "kse"

    override fun getLocationsPrefix(environment: ConfigurableEnvironment, application: SpringApplication): Array<String> =
            arrayOf(
                    "classpath:kse",
                    "classpath:META-INF/kse",
                    "classpath:config/kse"
            )

}
