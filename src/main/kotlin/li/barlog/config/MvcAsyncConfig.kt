package li.barlog.app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.util.concurrent.ThreadPoolExecutor

@Configuration
open class MvcAsyncConfig : WebMvcConfigurerAdapter() {
	override fun configureAsyncSupport(configurer: AsyncSupportConfigurer) {
		configurer.setTaskExecutor(mvcTaskExecutor())
	}

	@Bean
	open fun mvcTaskExecutor() = run {
		val taskExecutor = ThreadPoolTaskExecutor()
		taskExecutor.corePoolSize = 4
		taskExecutor.maxPoolSize = 16
		taskExecutor.setQueueCapacity(8)
		taskExecutor.setRejectedExecutionHandler(ThreadPoolExecutor.AbortPolicy())
		taskExecutor.threadNamePrefix = "spring-mvc-"
		taskExecutor.setThreadGroupName("spring-mvc-tg")

		taskExecutor
	}
}
