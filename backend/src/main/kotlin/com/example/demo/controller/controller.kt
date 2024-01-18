package com.example.demo.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.beans.factory.annotation.Autowired

import com.example.demo.repo.Repository
import com.example.demo.model.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@RestController
@RequestMapping("webTest")
class WebController {
    @Autowired
    lateinit var repository: Repository

    @PostMapping("/save")
    fun save(): String {
        repository.save(User("Jack", "Smith"))
        repository.save(User("Jill", "Anderson"))
        repository.save(User("Daddy", "Yankee"))

        return "Done"
    }

    @GetMapping("/findall")
    fun findAll(): MutableIterable<User> = repository.findAll()

    @GetMapping("/findbyid/{id}")
    fun findById(@PathVariable id: Long) = repository.findById(id)

    @GetMapping("/findbylastname/{lastName}")
    fun findByLastName(@PathVariable lastName: String): Iterable<User> = repository.findByLastName(lastName)
}