# Aoe2M: Age of Empires 2 Manipulator <img src="https://img.shields.io/badge/Aoe2M-1.0-green.svg"></img>
A world and scenario editor for [Age of Empires II: The Conquerors](https://en.wikipedia.org/wiki/Age_of_Empires_II:_The_Conquerors). AOE2 is a game developed by [Ensemble Studios](https://en.wikipedia.org/wiki/Ensemble_Studios) of Microsoft.

The editor aims to simplify scenario editing, especially batch editing (like tesselation, per-pixel trigger, terrain movement, etc) and cross scenario edit (like terrain cloning, terrain+unit cloning, trigger cloning), etc.


### Preface
I am a [software engineer](https://en.wikipedia.org/wiki/Software_engineer) from Software Engineering department, NJU, China. I graduate with CG (Computer Graphics) specification. I use my knowledge to reveal the requirements, design the software, develop the code, and ensure quality at my best effort.

I would describe me as a fan of AOE2. I first contacted AOE2 when I was at grade 3, primary school. I've kept on playing it till my high school. I acquainted many friend by discussing about AOE2 game experience. The most enjoyable part of AOE2 is playing campaigns designed by different author. A campaign is a series of scenarios connected by their plot. Each campaign depicts a brand new world and bring much joy to me.

The idea of this software emerge from [AOKTS](https://github.com/mullikine/aokts). AOKTS is capable of editing scenario file without many constrains from the embedded scenario editor. And without these constrains, a scenario author can perform some "acrobatic" edit.

I am not satisfied with editing scenario with just ESE+AOKTS. I think there should be more proficiency way to edit a scenario, especially when we are to do some "BIG", "abstract" work.

As is introduced, I've stopped paying attentions to AOE2 after high school, some thing in the editor might be real legacy. Feel free to contact me and show me new things, or your thought of what this editor should be.


### Workflow
The users either create an scenario or import a existing scenario (`.scx`) from their computer. The editor should try its best to retain the original data from the scenario.

The import process converts `.scx` file into `.a2m` file. And subsystems of Aoe2m will manipulate on `.a2m` file. The users can save their modification to a `.a2m` file, and recover their edit by opening `.a2m` file. Sharing are also permitted by exchanging `.a2m` files.

When they have finished editing in Aoe2M, they export their scenario back to `.scx` format, and arrange them into campaign or edit on using ESE or other things like AOKTS.
