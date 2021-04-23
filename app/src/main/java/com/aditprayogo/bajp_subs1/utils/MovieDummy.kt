package com.aditprayogo.bajp_subs1.utils

import com.aditprayogo.bajp_subs1.R
import com.aditprayogo.bajp_subs1.data.local.Movie

/**
 * Created by Aditiya Prayogo.
 */
object MovieDummy {

    fun getMovies(): List<Movie> = listOf(
            Movie(
                    id = "mov1",
                    title = "A Star Is Born",
                    overview = "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Allys career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                    genre = "Drama, Romance, Music",
                    image = R.drawable.poster_a_start_is_born,
                    dateOfRealese = "02/14/2019",
                    duration = "2h 16m"
            ),
            Movie(
                    id = "mov2",
                    title = "Alita: Battle Angel",
                    overview = "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                    genre = "Action, Science Fiction, Adventure",
                    image = R.drawable.poster_alita,
                    dateOfRealese = "02/14/2019",
                    duration = "2h 2m"
            ),
            Movie(
                    id = "mov3",
                    title = "Aquaman",
                    overview = "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm\'s half-human, half-Atlantean brother and true heir to the throne.",
                    genre = "Action, Adventure, Fantasy",
                    image = R.drawable.poster_aquaman,
                    dateOfRealese = "12/21/2018",
                    duration = "2h 23m"
            ),
            Movie(
                    id = "mov4",
                    title = "Bohemian Rhapsody",
                    overview = "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock and roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                    genre = "Music, Drama, History",
                    image = R.drawable.poster_bohemian,
                    dateOfRealese = "11/02/2018",
                    duration = "2h 15m"
            ),
            Movie(
                    id = "mov5",
                    title = "Cold Pursuit",
                    overview = "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking\'s associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                    genre = "Action, Crime, Thriller",
                    image = R.drawable.poster_cold_persuit,
                    dateOfRealese = "02/08/2019",
                    duration = "1h 59m"
            ),
            Movie(
                    id = "mov6",
                    title = "Creed II",
                    overview = "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life",
                    genre = "Drama",
                    image = R.drawable.poster_creed,
                    dateOfRealese = "11/21/2018",
                    duration = "2h 10m"
            ),
            Movie(
                    id = "mov7",
                    title = "Fantastic Beasts: The Crimes of Grindelwald",
                    overview = "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                    genre = "Adventure, Fantasy, Drama",
                    image = R.drawable.poster_crimes,
                    dateOfRealese = "11/16/2018",
                    duration = "2h 14m"
            ),
            Movie(
                    id = "mov8",
                    title = "Glass",
                    overview = "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                    genre = "Thriller, Drama, Science Fiction",
                    image = R.drawable.poster_glass,
                    dateOfRealese = "01/18/2019",
                    duration = "2h 9m"
            ),
            Movie(
                    id = "mov9",
                    title = "How to Train Your Dragon",
                    overview = "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father",
                    genre = "Fantasy, Adventure, Animation, Family",
                    image = R.drawable.poster_how_to_train,
                    dateOfRealese = "03/26/2010",
                    duration = "1h 38m"
            ),
            Movie(
                    id = "mov10",
                    title = "Avengers: Infinity War",
                    overview = "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                    genre = "Adventure, Action, Science Fiction",
                    image = R.drawable.poster_infinity_war,
                    dateOfRealese = "04/27/2018",
                    duration = "2h 29m"
            )
    )

    fun getTvShow(): List<Movie> = listOf(
            Movie(
                    id = "tv1",
                    title = "Arrow",
                    overview = "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                    genre = "Crime, Drama, Mystery, Action & Adventure",
                    image = R.drawable.poster_arrow,
                    dateOfRealese = "2012",
                    duration = "42m"
            ),
            Movie(
                    id = "tv2",
                    title = "Doom Patrol",
                    overview = "The Doom Patrol\'s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                    genre = "Sci-Fi, Fantasy, Comedy, Drama",
                    image = R.drawable.poster_doom_patrol,
                    dateOfRealese = "2019",
                    duration = "49m"
            ),
            Movie(
                    id = "tv3",
                    title = "Dragon Ball",
                    overview = "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish",
                    genre = "Animation, Action & Adventure, Sci-Fi & Fantasy",
                    image = R.drawable.poster_dragon_ball,
                    dateOfRealese = "1986",
                    duration = "25m"
            ),
            Movie(
                    id = "tv4",
                    title = "Fairy Tail",
                    overview = "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn\'t just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                    genre = "Action & Adventure, Animation, Comedy, Sci-Fi & Fantasy",
                    image = R.drawable.poster_fairytail,
                    dateOfRealese = "2009",
                    duration = "25m"
            ),
            Movie(
                    id = "tv5",
                    title = "Family Guy",
                    overview = "Sick, twisted, politically incorrect and Freakin Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                    genre = "Animation, Comedy",
                    image = R.drawable.poster_family_guy,
                    dateOfRealese = "1999",
                    duration = "22m"
            ),
            Movie(
                    id = "tv6",
                    title = "The Flash",
                    overview = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                    genre = "Drama, Sci-Fi & Fantasy",
                    image = R.drawable.poster_flash,
                    dateOfRealese = "2014",
                    duration = "44m"
            ),
            Movie(
                    id = "tv7",
                    title = "Gotham",
                    overview = "Everyone knows the name Commissioner Gordon. He is one of the crime world\'s greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                    genre = "Drama, Crime, Sci-Fi & Fantasy",
                    image = R.drawable.poster_gotham,
                    dateOfRealese = "2014",
                    duration = "43m"
            ),
            Movie(
                    id = "tv8",
                    title = "Grey Anatomy",
                    overview = "Follows the personal and professional lives of a group of doctors at Seattle\'s Grey Sloan Memorial Hospital.",
                    genre = "Drama",
                    image = R.drawable.poster_grey_anatomy,
                    dateOfRealese = "2005",
                    duration = "43m"
            ),
            Movie(
                    id = "tv9",
                    title = "Hanna",
                    overview = "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                    genre = "Action & Adventure, Drama",
                    image = R.drawable.poster_hanna,
                    dateOfRealese = "2019",
                    duration = "50m"
            ),
            Movie(
                    id = "tv10",
                    title = "Marvel Iron Fist",
                    overview = "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                    genre = "Action & Adventure, Drama, Sci-Fi & Fantasy",
                    image = R.drawable.poster_iron_fist,
                    dateOfRealese = "2017",
                    duration = "55m"
            )
    )

}