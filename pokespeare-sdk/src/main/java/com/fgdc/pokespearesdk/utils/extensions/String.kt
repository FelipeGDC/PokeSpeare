package com.fgdc.pokespearesdk.utils.extensions

fun String.escapeHtml(): String {
    var res = this
    res = res.replace("\n", " ")
    res = res.replace("\u000C", " ")
    return res
}