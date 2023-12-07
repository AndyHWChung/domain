val scala2_12 = "2.12.15"
val scala2_13 = "2.13.12"

ThisBuild / scalaVersion       := scala2_13
ThisBuild / majorVersion       := 9
ThisBuild / isPublicArtefact   := true

lazy val domain = (project in file("."))
  .settings(publish / skip := true)
  .aggregate(
    play28,
    play29,
    play30
  )

lazy val commonSettings =
  ScalariformSettings() ++ ScalariformSettings()

lazy val play28 = Project("domain-play-28", file("play-28"))
  .settings(
    crossScalaVersions := Seq(scala2_12, scala2_13),
    libraryDependencies ++= LibDependencies.play28 ++ LibDependencies.test,
    sharedSources
  )
  .settings(commonSettings)

lazy val play29 = Project("domain-play-29", file("play-29"))
  .settings(
    libraryDependencies ++= LibDependencies.play29 ++ LibDependencies.test,
    sharedSources
  )
  .settings(commonSettings)

lazy val play30 = Project("domain-play-30", file("play-30"))
  .settings(
    libraryDependencies ++= LibDependencies.play30 ++ LibDependencies.test,
    sharedSources
  )
  .settings(commonSettings)

def sharedSources = Seq(
  Compile / unmanagedSourceDirectories   += baseDirectory.value / "../shared/src/main/scala",
  Compile / unmanagedResourceDirectories += baseDirectory.value / "../shared/src/main/resources",
  Test    / unmanagedSourceDirectories   += baseDirectory.value / "../shared/src/test/scala",
  Test    / unmanagedResourceDirectories += baseDirectory.value / "../shared/src/test/resources"
)