package com.yura.bwxplore.data

object LocationData {
    private val data: List<Location> =
        listOf(
            Location(
                0,
                "Watu Dodol",
                -8.094560577578243F,
                114.41171910797284F,
                "https://www.yukbanyuwangi.co.id/wp-content/uploads/2020/04/pantai-grand-watu-dodol-banyuwangi.jpg",
                false
            ),
            Location(
                1,
                "Kawah Ijen",
                -8.062359115753027F,
                114.24604776917982F,
                "https://theworldtravelguy.com/wp-content/uploads/2021/07/DJI_0126_1200.jpg",
                true
            ),
            Location(
                2,
                "Pantai Sukamade",
                -8.560911370804323F,
                113.8866148989945F,
                "https://cda.1001malam.com/uploads/landmarks/pantaisukamade_banyuwangi_543.jpg",
                false
            ),
            Location(
                3,
                "Teluk Hijau",
                -8.563355527773725F,
                113.9240750278306F,
                "https://cdn.nativeindonesia.com/foto/2015/03/pantai-teluk-hijau-banyuwangi.jpg",
                true
            ),
            Location(
                4,
                "Pantai Rajegwesi",
                -8.558404649165906F,
                113.93743555627218F,
                "https://ksmtour.com/media/images/articles11/pantai-rajegwesi-banyuwangi-jawa-timur.jpg",
                false
            )
        )

    val listData: ArrayList<Location>
        get() {
            val list = ArrayList<Location>()
            for (aData in data) {
                val data = Location()
                data.id = aData.id
                data.name = aData.name
                data.lat = aData.lat
                data.long = aData.long
                data.imageUrl = aData.imageUrl
                data.popular = aData.popular

                list.add(data)
            }
            return list
        }
}

