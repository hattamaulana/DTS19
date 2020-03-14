package id.go.kominfo.dts;

import id.go.kominfo.dts.models.Book;

/**
 * Class ini hanya digunakan untuk men define
 * data yang dibutuhkan List view.
 *
 * @var Data.HOST : URI Server
 * @var Data.BOOKS : list data.
 */

public class Data {

    // HOST atau URI Server menggunakan lokal
    // dengan mengkopikan semua gambar buku yang digunakan di list data buku
    // ke dalam file secara default
    // /var/www/html/dts (Linux) atau C:\xampp\htdocs\dts (Windows).
    public static final String HOST = "http://192.168.65.1/dts/";
    public static final Book[] BOOKS =
            new Book[]{
                    new Book(
                            "sk2k.jpg",
                            "Sepasang Kaos Kaki Hitam",
                            "Ari Ginting",
                            5
                    ),
                    new Book(
                            "d90.jpeg",
                            "Dilan 1990",
                            "Pidi Baiq",
                            4
                    ),
                    new Book(
                            "d91.jpg",
                            "Dilan 1991",
                            "Pidi Baiq",
                            4
                    ),
                    new Book(
                            "sbbam.jpg",
                            "Sebuah Seni untuk Bersikap Bodo Amat",
                            "Mark Manson",
                            3
                    ),
                    new Book(
                            "da.jpeg",
                            "Distilasi Alkena",
                            "Wira Nagara",
                            4
                    ),
                    new Book(
                            "di.jpg",
                            "Distoria Inersia",
                            "Wira Nagara",
                            4
                    ),
                    new Book(
                            "rfh.jpg",
                            "Running For Hope",
                            "Dena Sikoembang",
                            5
                    ),
                    new Book(
                            "tiwinu.jpg",
                            "This is Why I Need You",
                            "Brian Khrisna",
                            2
                    ),
            };
}
