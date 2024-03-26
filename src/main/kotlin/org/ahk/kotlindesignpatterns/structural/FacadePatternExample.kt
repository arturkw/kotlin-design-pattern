package org.ahk.kotlindesignpatterns.structural


enum class Lang {
    ENG,
    DE,
    FR
}

interface VideoService {
    fun displayVideo(movieTitle: String)
}

interface AudioService {
    fun playAudio(movieTitle: String, lang: Lang)
}

interface SubtitleService {
    fun displaySubtitles(movieTitle: String, lang: Lang)
}

class MovieFacade(
    val videoService: VideoService,
    val audioService: AudioService,
    val subtitleService: SubtitleService
) {

    fun playMovie(movieTitle: String, audioLang: Lang, subtitlesLang: Lang) {
        videoService.displayVideo(movieTitle)
        audioService.playAudio(movieTitle, audioLang)
        subtitleService.displaySubtitles(movieTitle, subtitlesLang)
    }

}

fun main(args: Array<String>) {
    val movieFacade = MovieFacade(object : VideoService {
        override fun displayVideo(movieTitle: String) {
            println("Displaying video for movie '$movieTitle'")
        }
    }, object : AudioService {
        override fun playAudio(movieTitle: String, lang: Lang) {
            println("Playing audio for movie '$movieTitle'. Selected audo language is $lang")
        }
    }, object : SubtitleService {
        override fun displaySubtitles(movieTitle: String, lang: Lang) {
            println("Displaying subtitles for movie '$movieTitle'. Selected subtitles language is $lang")
        }
    })
    movieFacade.playMovie("Titanic", Lang.DE, Lang.ENG)
}