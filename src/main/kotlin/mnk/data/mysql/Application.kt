package mnk.data.mysql

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("mnk.data.mysql")
		.start()
}

