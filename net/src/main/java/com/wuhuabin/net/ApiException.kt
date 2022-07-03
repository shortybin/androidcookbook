package com.wuhuabin.net

import java.io.IOException

class ApiException(val errorCode: Int, val errorMessage: String) : IOException()