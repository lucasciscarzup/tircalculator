package br.com.test.repository

import br.com.test.model.Tir
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@Repository
interface TirRepository: CrudRepository<Tir, UUID> {
}