package com.example.ohouse.domain

import com.example.ohouse.domain.repository.network.OhouseRepositoryNetwork
import javax.inject.Inject

class OhouseRepository @Inject constructor(
    val network: OhouseRepositoryNetwork
)