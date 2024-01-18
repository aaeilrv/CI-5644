package com.example.demo.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.beans.factory.annotation.Autowired

import com.example.demo.repo.Repository
import com.example.demo.model.User

@RestController
class WebController {
    @Autowired
    lateinit var repository: Repository

    @RequestMapping("/save")
    fun save(): String {
        repository.save(User("Jack", "Smith"))
        repository.save(User("Jill", "Anderson"))
        repository.save(User("Daddy", "Yankee"))

        return "Done"
    }

    @RequestMapping("/findall")
    fun findAll(): MutableIterable<User> = repository.findAll()

    @RequestMapping("/findbyid/{id}")
    fun findById(@PathVariable id: Long) = repository.findById(id)

    @RequestMapping("/findbylastname/{lastName}")
    fun findByLastName(@PathVariable lastName: String): Iterable<User> = repository.findByLastName(lastName)
}