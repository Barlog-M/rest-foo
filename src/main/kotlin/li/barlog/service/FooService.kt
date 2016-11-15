package li.barlog.service

import li.barlog.model.Foo
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.time.LocalDateTime
import java.util.Random
import com.ibm.icu.util.ULocale
import com.ibm.icu.text.RuleBasedNumberFormat

@Service
open class FooService {
	companion object {
		private val rule = RuleBasedNumberFormat(ULocale("ru_RU"), RuleBasedNumberFormat.SPELLOUT)
	}

	private val rnd = SecureRandom(Random().nextLong().toString().toByteArray())

	open fun createFoo() = run {
		val l = rnd.nextLong()
		Foo(l, LocalDateTime.now(), long2literal(l))
	}

	private fun long2literal(l: Long) = rule.format(l)
}
