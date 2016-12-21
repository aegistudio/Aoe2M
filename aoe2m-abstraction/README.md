# Common Abstraction Module
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

### ModelObject
`ModelObjects` represent objects that we intends to display, to edit and to "hack".

In a more detailed word, the extension developer expand the function by providing:
- More `ModelObjects`. E.g. pseudo triggers, pseudo units, etc.
- More `Commands` to manipulate existing `ModelObject` or your own `ModelObject`.
- More `View` to display and edit `ModelObject` (mainly your own `ModelObject`).

`ModelObjects` are placed in model package. You will find there's even no getter/setter method! Why?!

That's because you are coerce to provide certain field presenting your getter/setter. Some pseudo object don't even need any field! So it's not necessary to provide any field, except for some containers to be capable of adding or removing elements. The `Command` manipulating certain `ModelObject` should be certain of what they are setting, and the `View` displaying should also be certain of what they should get.

Though no field is need to be provided, you still need to notify view when inner state of your object changes. Persisting (read from / write to .A2M model files) and marshaling (map to .SCX files) should also be present.

Though `ModelObjects` in the model package may fail to cover development process, it is abstracted from SCX format. Please make sure you're able to map into certain segment of Scenario object if you insist in adding out-of-model objects.