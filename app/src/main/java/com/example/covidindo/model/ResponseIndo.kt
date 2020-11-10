package com.example.covidindo.model

import com.google.gson.annotations.SerializedName

data class ResponseIndo(

	@field:SerializedName("wismaAtlet")
	val wismaAtlet: WismaAtlet? = null,

	@field:SerializedName("perHari")
	val perHari: PerHari? = null,

	@field:SerializedName("jumlahKasus")
	val jumlahKasus: Int? = null,

	@field:SerializedName("meninggal")
	val meninggal: Int? = null,

	@field:SerializedName("sembuh")
	val sembuh: Int? = null,

	@field:SerializedName("perawatan")
	val perawatan: Int? = null,

	@field:SerializedName("perKasus")
	val perKasus: PerKasus? = null,

	@field:SerializedName("perProvinsi")
	val perProvinsi: PerProvinsi? = null
)

data class WismaAtlet(

	@field:SerializedName("ruangan")
	val ruangan: String? = null,

	@field:SerializedName("json")
	val json: String? = null,

	@field:SerializedName("karyawan")
	val karyawan: String? = null,

	@field:SerializedName("kasur")
	val kasur: String? = null
)

data class PerKasus(

	@field:SerializedName("old")
	val old: String? = null,

	@field:SerializedName("csv")
	val csv: String? = null,

	@field:SerializedName("json")
	val json: String? = null,

	@field:SerializedName("links")
	val links: String? = null
)

data class PerHari(

	@field:SerializedName("csv")
	val csv: String? = null,

	@field:SerializedName("json")
	val json: String? = null
)

data class PerProvinsi(

	@field:SerializedName("csv")
	val csv: String? = null,

	@field:SerializedName("json")
	val json: String? = null
)
