# Common Abstraction Module
The `Abstraction` aims to provide interfaces and protocols between different artifacts and extensions.


### Architecture
The Aoe2M editor is designed to be of MVC (model-view-controller) architecture.

[MVC](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) is an common used architecture, where:
- `Model` possesses data and logic. Its logics are started by `Controller`, and it notifies `View` when changes in the data.
- `View` displays the data. It retrieves data from `Model`, and passes input to `Controller`.
- `Controller` receives input from `View`, process input and translate to concrete operation on `Model`.

As an overview of Aoe2M, the software just contains two major modules: `Core` and `View`. But we provides `Extension` mechanism 
as the way to develop the software.

The `Model` is managed by `Core`, its data are represented by `ModelObject` and logics are represented by `Command`.

The only way `View` could ask `Core` to do things is just by issuing commands (in `String` form). Thus variance of `Controller` is not required.

The `View` also register notifier to corresponding `Observers`. When update happens in `Core` (or `Model`), it fires `Observer` and `View` will be notified then.


### ModelObject <img src="https://img.shields.io/badge/For-CoreDeveloper-green.svg"></img>
`ModelObjects` represent objects that we intends to display, to edit and to "hack".

In a more detailed word, the extension developer expand the function by providing:
- More `ModelObjects`. E.g. pseudo triggers, pseudo units, etc.
- More `Commands` to manipulate existing `ModelObject` or your own `ModelObject`.
- More `View` to display and edit `ModelObject` (mainly your own `ModelObject`).

`ModelObjects` are placed in model package. You will find there's even no getter/setter method! Why?!

That's because you are coerce to provide certain field presenting your getter/setter. Some pseudo object don't even need any field! So it's not necessary to provide any field, except for some containers to be capable of adding or removing elements. The `Command` manipulating certain `ModelObject` should be certain of what they are setting, and the `View` displaying should also be certain of what they should get.

Though no field is required to be provided, you still need to notify view when inner state of your object changes. Also be able to persist (read from / write to .A2M model files) and marshal (map to .SCX files).

Though `ModelObjects` in the model package may fail to cover development process, it is abstracted from SCX format. Please make sure you're able to map into certain segment of Scenario object if you insist in adding out-of-model objects.


### Command <img src="https://img.shields.io/badge/For-CoreDeveloper-green.svg"></img>

`Command` represents operations requesting to be done. Your user "issue" a command in a variety of ways. Besides directly typing in command line, filling a form, adding a trigger, and even selecting a unit, will cause a command to be spoken out, ... in `String` form. You will never know where your user type in the command, but to request is to be responded. You just need to execute when you receive one.

You could add your command when your extension get `boot`, by registering it to `CoreModel`. A command can either be reverted or not. If your command can be reverted, please ensure you've ask your user to confirm, by using `Reaction` from `CoreModel`. If your command can be reverted, just implement the `Action` telling how to go forward and revert.

As users are allowed to type in commands, separated by space, it would be really hard to get things right when spaces, quotes and slashes occurs. So add your own `CommandParseur` and defines how users can construct their command so as your view. Your command would also like to provide tab-complete. Just look up the last parameter and tell your user what to type in next.


### Observer <img src="https://img.shields.io/badge/For-ViewDeveloper-blue.svg"></img>
`Observer` (aka, [Observer Pattern](https://en.wikipedia.org/wiki/Observer_pattern)) provides an [implicit invocation](https://en.wikipedia.org/wiki/Implicit_invocation) style interface. This is helpful when there're multiple view objects capturing the inner state of an model object. The work flow of `Observer` will be:
- `View` object subscribes to `Registration` of an `Model` object they would like to display.
- `Model` object captures its inner state changes and fires the observer `Registration`.
- The `Registration` sub-invokes its `Observer` list, notifying changes.
- The `Observer` captures the changes, and update the display.
- When `View` gets destroyed or in other cases, it unsubscribes from the `Registration`.
