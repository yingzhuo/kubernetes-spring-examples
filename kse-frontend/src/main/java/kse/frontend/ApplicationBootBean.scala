package kse.frontend

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(Array(kse.BasePackageName))
protected class ApplicationBootBean {
}
