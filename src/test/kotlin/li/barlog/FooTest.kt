package li.barlog

import li.barlog.model.Foo
import li.barlog.model.Ok
import li.barlog.service.FooService

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.time.LocalDateTime

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(
	classes = arrayOf(App::class),
	webEnvironment = WebEnvironment.RANDOM_PORT
)
class FooTest() {
	val headers = HttpHeaders()

	@Autowired
	lateinit var template: TestRestTemplate

	@MockBean
	lateinit var fooService: FooService

	val foo = Foo(1, LocalDateTime.of(1999, 1, 20, 15, 30), "foo")

	@Before
	fun init() {
		headers.contentType = MediaType.APPLICATION_JSON_UTF8
		headers.accept = listOf(MediaType.APPLICATION_JSON_UTF8)

		`when`(fooService.createFoo()).thenReturn(foo)
	}

	@Test
	fun testGet() {
		val req = HttpEntity<Void>(headers)
		val res = template.exchange("/foo", HttpMethod.GET, req, Foo::class.java)
		assertEquals(res.statusCode, HttpStatus.OK)
		assertEquals(res.body, foo)
	}

	@Test
	fun testPost() {
		val req = HttpEntity<Foo>(foo, headers)
		val res = template.exchange("/foo", HttpMethod.POST, req, Ok::class.java)
		assertEquals(res.statusCode, HttpStatus.OK)
		assertEquals(res.body, Ok())
	}

	@Test
	fun testPut() {
		val req = HttpEntity<Foo>(foo, headers)
		val res = template.exchange("/foo", HttpMethod.PUT, req, Ok::class.java)
		assertEquals(res.statusCode, HttpStatus.OK)
		assertEquals(res.body, Ok())
	}

	@Test
	fun testDelete() {
		val req = HttpEntity<Void>(headers)
		val res = template.exchange("/foo/1", HttpMethod.DELETE, req, Ok::class.java)
		assertEquals(res.statusCode, HttpStatus.OK)
		assertEquals(res.body, Ok())
	}
}
