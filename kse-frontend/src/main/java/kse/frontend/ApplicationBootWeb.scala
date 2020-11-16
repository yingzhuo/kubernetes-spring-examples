package kse.frontend

import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ServletComponentScan(Array(kse.BasePackageName))
protected class ApplicationBootWeb {
}
