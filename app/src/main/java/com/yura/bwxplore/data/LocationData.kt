package com.yura.bwxplore.data

import com.yura.bwxplore.data.firebase.entities.Location

object LocationData {
    private val data: List<Location> =
        listOf(
            Location(
                0,
                "Pantai Sukamade",
                -8.560359595137133,
                113.88675432972636,
                "https://cda.1001malam.com/uploads/landmarks/pantaisukamade_banyuwangi_543.jpg",
                true
            ),
            Location(
                1,
                "Teluk Hijau",
                -8.559988783671402, 113.92795764798822,
                "https://cdn.nativeindonesia.com/foto/2015/03/pantai-teluk-hijau-banyuwangi.jpg",
                true
            ),
            Location(
                2,
                "Pantai Rajegwesi",
                -8.558091633884267, 113.93619763219445,
                "https://ksmtour.com/media/images/articles11/pantai-rajegwesi-banyuwangi-jawa-timur.jpg",
                false
            ),
            Location(
                3,
                "Pantai Wedi Ireng",
                -8.596931469253352, 113.98928103404944,
                "https://sikidang.com/wp-content/uploads/Pantai-Wedi-Ireng-Banyuwangi.jpg",
                false
            ),
            Location(
                4,
                "Pantai Pulau Merah",
                -8.59916320275761, 114.02969687390544,
                "https://anekatempatwisata.com/wp-content/uploads/2015/08/Pantai-Pulau-Merah-Banyuwangi-ferdiozom_.jpg",
                true
            ),
            Location(
                5,
                "Pantai Mustika",
                -8.590177328869952, 114.00457442690539,
                "https://media9.co.id/wp-content/uploads/2020/03/1_KwQEnYK_wOJvoume-uHcjg-1140x760-1.jpg",
                false
            ),
            Location(
                6,
                "Waduk Sidodadi",
                -8.328819797502398, 114.04927472247178,
                "https://www.tempatwisata.pro/media/uploads/3092/f10eaa7e9a53cb76e55ee0b15d240c57.jpg",
                false
            ),
            Location(
                7,
                "X-Badeng Adventure",
                -8.21419005559471, 114.1775483683203,
                "https://www.tamansurgabwi.co.id/wp-content/uploads/2018/12/X-Badeng-Banyuwangi-Top.png",
                true
            ),
            Location(
                8,
                "Karo Adventure",
                -8.217580650478466, 114.17830588199037,
                "https://cdn0-production-images-kly.akamaized.net/4R11cYuk0gL4Tko1lqL635gjs-A=/1200x1200/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/2786528/original/045353400_1556072831-1__3_.jpg",
                false
            ),
            Location(
                9,
                "Kawah Ijen",
                -8.062297178604055, 114.24609228252737,
                "https://theworldtravelguy.com/wp-content/uploads/2021/07/DJI_0126_1200.jpg",
                true
            ),
            Location(
                10,
                "Desa Wisata Agriculture Kampung Kopi",
                -8.13553301094486, 114.32786448991618,
                "https://www.ngopibareng.id/images/imagecache/20171020202017img8843.JPG",
                false
            ),
            Location(
                11,
                "Desa Wisata Agriculture Tamansari",
                -8.167489136964056, 114.25323401547499,
                "https://asiatoday.id/wp-content/uploads/2019/07/cropped-Desa-Tamansari-ok-800x445.jpg",
                false
            ),
            Location(
                12,
                "Bangsring Underwater",
                -8.053650975037776, 114.43075899672368,
                "https://banyuwangitourism.com/jalanjalan/themes/AdminLTE/img/destinasti/2.jpg",
                false
            ),
            Location(
                13,
                "Grand Watu Dodol",
                -8.087743502744706, 114.41586200573163,
                "https://www.yukbanyuwangi.co.id/wp-content/uploads/2020/04/pantai-grand-watu-dodol-banyuwangi.jpg",
                false,
            ),
            Location(
                14,
                "Marina Boom Beach",
                -8.206878068947168, 114.38489542137133,
                "https://media9.co.id/wp-content/uploads/2020/02/IMG-20200212-WA0049.jpg",
                true,
            ),
            Location(
                15,
                "Samudera Selfie",
                -8.255678524130609, 114.37667767976912,
                "https://pinhome-blog-assets-public.s3.amazonaws.com/2017/12/Samudra-Selfie-Resto-Banyuwangi.jpg",
                false,
            ),
            Location(
                16,
                "Pantai Cemara",
                -8.263258962534053, 114.37298500470125,
                "https://pariwisatabanyuwangi.com/wp-content/uploads/2018/06/Pantai-Cemara-Banyuwangi.jpg",
                false,
            ),
            Location(
                17,
                "Savana Sadengan",
                -8.650403362312352, 114.37135076565123,
                "https://img.okezone.com/content/2017/02/04/406/1609434/padang-savana-sadengan-hadirkan-pertualangan-bak-di-afrika-uCB52f2uVO.JPG",
                false,
            ),
            Location(
                18,
                "Pantai Plengkung",
                -8.675168718387175, 114.37309447629893,
                "https://upload.wikimedia.org/wikipedia/id/d/dd/Plengkungombak.jpg",
                true,
            ),
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

