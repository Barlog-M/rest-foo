package li.barlog.rest

import li.barlog.model.Foo
import li.barlog.model.Ok
import li.barlog.service.FooService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable

@RestController
@RequestMapping(
	"/bar",
	consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
	produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
)
class BarController @Autowired constructor(
	val fooService: FooService
) {
	companion object {
		private val log = LoggerFactory.getLogger(FooController::class.java)
	}

	@GetMapping
	fun get() = Callable {
		log.info("get")
		ResponseEntity.ok(fooService.createFoo())
	}

	@PostMapping
	fun post(@RequestBody foo: Foo) = Callable {
		log.info("post: $foo")
		ResponseEntity.ok(Ok())
	}

	@PutMapping
	fun put(@RequestBody foo: Foo) = Callable {
		log.info("put: $foo")
		ResponseEntity.ok(Ok())
	}

	@DeleteMapping("/{id}")
	fun delete(@PathVariable("id") id: String) = Callable {
		log.info("delete: $id")
		ResponseEntity.ok(Ok())
	}
}
