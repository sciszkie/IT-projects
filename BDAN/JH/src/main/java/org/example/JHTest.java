package org.example;
import org.junit.jupiter.api.Test;

import static org.example.JH.hH_1;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JHTest {
    @Test
    public void hash1() {
        String text = "Stasio";
        String[] bl = JH.blocks(text);
        int number = 256;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("2902650057525ca1fcea0653c6d64e2cd49f12c9e1c795718201360af91197a9", hash);
    }

    @Test
    public void hash2() {
        String text = "";
        String[] bl = JH.blocks(text);
        int number = 224;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("2c99df889b019309051c60fecc2bd285a774940e43175b76b2626630", hash);
    }

    @Test
    public void hash3() {
        String text = "";
        String[] bl = JH.blocks(text);
        int number = 256;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("46e64619c18bb0a92a5e87185a47eef83ca747b8fcc8e1412921357e326df434", hash);
    }

    @Test
    public void hash4() {
        String text = "";
        String[] bl = JH.blocks(text);
        int number = 384;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("2fe5f71b1b3290d3c017fb3c1a4d02a5cbeb03a0476481e25082434a881994b0ff99e078d2c16b105ad069b569315328", hash);
    }

    @Test
    public void hash5() {
        String text = "";
        String[] bl = JH.blocks(text);
        int number = 512;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("90ecf2f76f9d2c8017d979ad5ab96b87d58fc8fc4b83060f3f900774faa2c8fabe69c5f4ff1ec2b61d6b316941cedee117fb04b1f4c5bc1b919ae841c50eec4f", hash);
    }

    @Test
    public void hash6() {
        String text = "The quick brown fox jumps over the lazy dog";
        String[] bl = JH.blocks(text);
        int number = 256;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("6a049fed5fc6874acfdc4a08b568a4f8cbac27de933496f031015b38961608a0", hash);
    }

    @Test
    public void hash7() {
        String text = "The quick brown fox jumps over the lazy dog.";
        String[] bl = JH.blocks(text);
        int number = 256;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("d001ae2315421c5d3272bac4f4aa524bddd207530d5d26bbf51794f0da18fafc", hash);
    }

    @Test
    public void hash8() {
        String text = "WEITI - wydzial elektroniki i technik informacyjnych";
        String[] bl = JH.blocks(text);
        int number = 384;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("849f72c7ed3539c8a305b1680bd7115f7b282939bdf377780bf149bd22ae951ca7f7ddf5be61b2816b1a2571b276bac9", hash);
    }

    @Test
    public void hash9() {
        String text = "Litwo, ty jestes jak zdrowie, ile Cie trzeba cenic ten tylko sie dowie kto Cie stracil";
        String[] bl = JH.blocks(text);
        int number = 256;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("85ef2fc3d667a5d28d3a498c77a4a3d5b47b6e9ec2d0b15d10056c9b26b7b192", hash);
    }

    @Test
    public void hash10() {
        String text = "Litwo, ty jestes jak zdrowie, ile Cie trzeba cenic ten tylko sie dowie kto Cie stracil";
        String[] bl = JH.blocks(text);
        int number = 512;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("16c86563298ae449cd37cb9a7b322b5b63844777d4994762ea7b041b87ccc3cf2570d6b37ae68191ff514f4c567734fd883945ef70cd5430a090ed2dfb44c7ee", hash);
    }

    @Test
    public void hash11() {
        String text = "Kuba";
        String[] bl = JH.blocks(text);
        int number = 384;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("72f28cdcf0fd19cc363441a5c5be2551419a63621e1b0fca671d8525dc1bc6b5787a6a9c48b0e40c6c2172df6dd8bc10", hash);
    }

    @Test
    public void hash12() {
        String text = "chcialbym, zeby mi sie chcialo tak jak mi sie nie chce, yhm";
        String[] bl = JH.blocks(text);
        int number = 224;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("2f500a2b4a8bbaa438a11517cc8527ddce54efbe111eccb123455c49", hash);
    }

    @Test
    public void hash13() {
        String text = "jedyne co musze zrobic w tym tygodniu to: projekt duzy z BDAN, projekt duzy z matmy, krawaty, kieliszki i sztucce; prezentacja z SYCY, laby z fizyki, laby z UAI, duzy projekt z APRO";
        String[] bl = JH.blocks(text);
        int number = 224;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("d130788d3efde632b119625f90fbbe84debe0ee6beb5c5cd952180b6", hash);
    }

    @Test
    public void hash14() {
        String text = "Mamy duzo egzaminow w sesji (baldzo duzo) i baldzo malo spimy";
        String[] bl = JH.blocks(text);
        int number = 512;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("dcbc19834707036d3a604c5f01c0a6348ca58226b443d387c71696871b37d28d612e2e87d4473fb1e703ee2861b82b13606afee83ff8e22bc4e62a6d58153f03", hash);
    }

    @Test
    public void hash15() {
        String text = "A lalalallom, a lalalalalom, a lalalalalomlommilombilom aaaaaa";
        String[] bl = JH.blocks(text);
        int number = 512;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("83d10c67ddf163aa9c15c1c80cf963717fc0c0e9d9a745d85c3e782774b9355c83d62bc3754946025ba5c8d3b64151a92955c88ad45e85e3dbc81b065b06f6df", hash);
    }

    @Test
    public void hash16() {
        String text = "To jest ostatni test hurra!!!";
        String[] bl = JH.blocks(text);
        int number = 256;
        String h = JH.f_F(hH_1(number), bl, bl.length);
        String hash = JH.cut_cut_cut(h, number);
        assertEquals("ac1a89328c71d1bfae02d7bac32ee4c9b130e7775915d559ff839717c7e8964c", hash);
    }

}