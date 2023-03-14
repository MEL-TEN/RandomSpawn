package io.github.mel_ten.randomspawn.config

data class Config (
    var ServerName: String = "[RandomSpawn]",

    var LimitX: Long = 5000,
    var LimitZ: Long = 5000,
    var FixedY: Long = 320,
)