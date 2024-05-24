package com.example.momoexam.utils

import com.example.momoexam.vo.ImportDate
import com.example.momoexam.vo.animal.AnimalInfo
import com.example.momoexam.vo.introduction.AreaIntroduction

object TestUtils {

    // 館區介紹測試資料
    val testAreaIntroduction = AreaIntroduction(
        eCategory = "戶外區",
        eGeo = "MULTIPOINT ((121.5805931 24.9985962))",
        eInfo = "臺灣位於北半球，北迴歸線橫越南部，造成亞熱帶溫和多雨的氣候。又因高山急流、起伏多樣的地形，因而在這蕞爾小島上，形成了多樣性的生態系，孕育了多種不同生態習性的動、植物，豐富的生物物種共存共榮於此，也使臺灣博得美麗之島「福爾摩沙」的美名。臺灣動物區以臺灣原生動物與棲息環境為展示重點，佈置模擬動物原生棲地之生態環境，讓動物表現如野外般自然的生活習性，引導民眾更正確地認識本土野生動物，為園區環境教育的重要據點。藉由提供動物寬廣且具隱蔽的生態環境，讓動物避開人為過度的干擾，並展現如野外般自然的生活習性和行為。展示區以多種臺灣的保育類野生動物貫連成保育廊道，包括臺灣黑熊、穿山甲、歐亞水獺、白鼻心、石虎、山羌等。唯有認識、瞭解本土野生動物，才能愛護、保育牠們，並進而珍惜我們共同生存的這塊土地！",
        eMemo = "",
        eName = "臺灣動物區",
        eNo = "1",
        ePicUrl = "http://www.zoo.gov.tw/iTAP/05_Exhibit/01_FormosanAnimal.jpg",
        eUrl = "https://youtu.be/QIUbzZ-jX_Y",
        id = 1,
        importDate = ImportDate(
            date = "2022-05-09 14:47:38.971190",
            timezoneType = 3,
            timezone = "Asia/Taipei"
        )
    )

    // 動物資料測試資料
    val testAnimalInfo: AnimalInfo = AnimalInfo(
        aAdopt = "",
        aAlsoknown = "",
        aBehavior = "1、國王企鵝在圈養環境下平均壽命約15-20年。\r\n 2、國王企鵝繁殖期會群聚在一起，繁殖期期間會有領域性，每對領域的範圍約1平方公尺。不築巢，每窩下一個蛋，由雌雄輪流孵蛋，孵化期平均約56天。雛鳥孵出時幾乎全裸，帶有一些短絨羽，第一次的絨羽淺灰或褐色，第二次則轉為暗褐色，約40天大加入幼鳥群，約10-13個月羽翼豐滿。約5-7歲達到性成熟。\r\n 3、國王企鵝野外族群估計超過兩百萬隻，呈增加的趨勢(國際自然保育聯盟IUCN於2008年評估)。",
        aCid = "2",
        aClass = "鳥綱",
        aCode = "KingPenguin",
        aConservation = "暫無危機 LC",
        aCrisis = "",
        aDiet = "主要以魚維生，野外的國王企鵝特別喜歡吃小型的燈籠魚科(Myctophidae)的魚類，也會吃頭足類(cephalopods)如魷魚(Moroteuthis)。",
        aDistribution = "次南極區。涵蓋南美福克蘭群島、喬治亞群島、南非南方海域及紐西蘭南方海域。",
        aFamily = "企鵝科",
        aFeature = "1、 國王企鵝是全世界體型第二大的企鵝(僅次於帝王企鵝)，嘴喙長、耳羽顏色偏橘，有一圈黑邊框，圖案類似水滴形。\r\n 2、 喉嚨至前胸部位為美麗的金黃色。\r\n 3、 體長大約95公分(測量企鵝的體長，是由嘴至尾拉長)。體重大約12～15Kg。在圈養環境下平均壽命約15至20年。",
        aGeo = "MULTIPOINT ((121.5907654 24.9931338))",
        aHabitat = "",
        aInterpretation = "",
        aKeywords = "企鵝；國王企鵝",
        aLocation = "企鵝館",
        aNameCh = "國王企鵝",
        aNameEn = "King Penguin",
        aNameLatin = "Aptenodytes patagonicus",
        aOrder = "企鵝目",
        aPdf01Alt = "",
        aPdf01Url = "",
        aPdf02Alt = "",
        aPdf02Url = "",
        aPhylum = "脊索動物門",
        aPic01Alt = "國王企鵝",
        aPic01Url = "http://www.zoo.gov.tw/iTAP/03_Animals/PenguinHouse/KingPenguin/KingPenguin_Pic01.jpg",
        aPic02Alt = "國王企鵝",
        aPic02Url = "http://www.zoo.gov.tw/iTAP/03_Animals/PenguinHouse/KingPenguin/KingPenguin_Pic02.jpg",
        aPic03Alt = "國王企鵝",
        aPic03Url = "http://www.zoo.gov.tw/iTAP/03_Animals/PenguinHouse/KingPenguin/KingPenguin_Pic03.jpg",
        aPic04Alt = "國王企鵝",
        aPic04Url = "http://www.zoo.gov.tw/iTAP/03_Animals/PenguinHouse/KingPenguin/KingPenguin_Pic04.jpg",
        aPoigroup = "",
        aSummary = "",
        aThemeName = "",
        aThemeUrl = "",
        aUpdate = "2017/9/29",
        aVedioUrl = "https://www.youtube.com/playlist?list=PLWYak5Af5-DuSoWNgHVIi8vH8AlN6Rzg4",
        aVoice01Alt = "",
        aVoice01Url = "",
        aVoice02Alt = "",
        aVoice02Url = "",
        aVoice03Alt = "",
        aVoice03Url = "",
        id = 2,
        importdate = ImportDate(
            date = "2022-12-23 14:05:14.684926",
            timezoneType = 3,
            timezone = "Asia/Taipei"
        )
    )

}