package com.shisheo.demo.utils

import com.google.gson.annotations.SerializedName

data class VerifyUserRequest(
    @SerializedName("merchant_id") val merchantId: Int,
    @SerializedName("branch_id") val branchId: Int,
    @SerializedName("user_pincode") val pinCode: Int
)