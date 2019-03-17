package com.lauwba.project.config

object Config {

    private const val Host="http://192.168.43.93/desalauwba/" //yang bakal berganti
    const val url_pengumuman= Host+"index.php/Pengumuman/select"
    const val url_gambar= Host+"assets/upload_pengumuman/"
    const val id="id"
    const val url_detail_pengumuman= Host+"index.php/Pengumuman/select_by_get/"
    val url_layanan = Host+"index.php/Layanan/select_by_get/"
    val url_detail_berita = Host+"index.php/Berita/select_by_get/"
    val url_berita = Host+"index.php/Berita/select/"
    val url_programdesa = Host+"index.php/Programdesa/select/"
    val url_potensidesa = Host+"index.php/Potensidesa/select/"
}