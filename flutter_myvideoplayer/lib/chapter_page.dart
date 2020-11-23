import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

import 'package:flutter_myvideoplayer/chapter_bloc.dart';

import 'chapter.dart';

class ChapterPage extends StatefulWidget {
  final String title;
  //Allows Todo card to be dismissable horizontally

  ChapterPage({Key key, this.title}) : super(key: key);

  @override
  _ChapterPageState createState() => new _ChapterPageState();
}

class _ChapterPageState extends State<ChapterPage> {
  final DismissDirection _dismissDirection = DismissDirection.horizontal;
  final ChapterBloc chapterBloc = ChapterBloc();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        resizeToAvoidBottomPadding: false,
        appBar: AppBar(
          title: Text("Chapter"),
        ),
        body: SafeArea(
          child: Container(
            color: Colors.white,
            padding: const EdgeInsets.only(left: 2.0, right: 2.0, bottom: 2.0),
            child: Container(
              //This is where the magic starts
              child: getTodosWidget(),
            ),
          ),
        ),
        bottomNavigationBar: BottomAppBar(
          color: Colors.white,
          child: Container(
            decoration: BoxDecoration(
                border: Border(
              top: BorderSide(color: Colors.grey, width: 0.3),
            )),
            child: Row(
              mainAxisSize: MainAxisSize.max,
              children: <Widget>[
                IconButton(
                    icon: Icon(
                      Icons.menu,
                      color: Colors.indigoAccent,
                      size: 28,
                    ),
                    onPressed: () {
                      //just re-pull UI for testing purposes
                      chapterBloc.getChapters();
                    }),
                Expanded(
                  child: Text(
                    "Todo",
                    style: TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.w600,
                        fontFamily: 'RobotoMono',
                        fontStyle: FontStyle.normal,
                        fontSize: 19),
                  ),
                ),
                Wrap(children: <Widget>[
                  IconButton(
                    icon: Icon(
                      Icons.search,
                      size: 28,
                      color: Colors.indigoAccent,
                    ),
                    onPressed: () {
                      // _showTodoSearchSheet(context);
                    },
                  ),
                  Padding(
                    padding: EdgeInsets.only(right: 5),
                  )
                ])
              ],
            ),
          ),
        ),
        floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
        floatingActionButton: Padding(
          padding: EdgeInsets.only(bottom: 25),
          child: FloatingActionButton(
            elevation: 5.0,
            onPressed: () {
              _showAddTodoSheet(context);
            },
            backgroundColor: Colors.white,
            child: Icon(
              Icons.add,
              size: 32,
              color: Colors.indigoAccent,
            ),
          ),
        ));
  }

  Widget getTodosWidget() {
    /*The StreamBuilder widget,
    basically this widget will take stream of data (todos)
    and construct the UI (with state) based on the stream
    */
    return StreamBuilder(
      stream: chapterBloc.chapter,
      builder: (BuildContext context, AsyncSnapshot<List<Chapter>> snapshot) {
        return getTodoCardWidget(snapshot);
      },
    );
  }

  Widget getTodoCardWidget(AsyncSnapshot<List<Chapter>> snapshot) {
    /*Since most of our operations are asynchronous
    at initial state of the operation there will be no stream
    so we need to handle it if this was the case
    by showing users a processing/loading indicator*/
    if (snapshot.hasData) {
      /*Also handles whenever there's stream
      but returned returned 0 records of Todo from DB.
      If that the case show user that you have empty Todos
      */
      return snapshot.data.length != 0
          ? ListView.builder(
              itemCount: snapshot.data.length,
              itemBuilder: (context, itemPosition) {
                Chapter chapter = snapshot.data[itemPosition];
                final Widget dismissibleCard = new Dismissible(
                    background: Container(
                      child: Padding(
                        padding: EdgeInsets.only(left: 10),
                        child: Align(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            "Deleting",
                            style: TextStyle(color: Colors.white),
                          ),
                        ),
                      ),
                      color: Colors.redAccent,
                    ),
                    onDismissed: (direction) {
                      /*The magic
                    delete Todo item by ID whenever
                    the card is dismissed
                    */
                      chapterBloc.deleteChapterByChapterNo(chapter.chapterNo);
                    },
                    direction: _dismissDirection,
                    key: new ObjectKey(chapter),
                    child: Card(
                      shape: RoundedRectangleBorder(
                        side: BorderSide(color: Colors.grey[200], width: 0.5),
                        borderRadius: BorderRadius.circular(5),
                      ),
                      color: Colors.white,
                      child: ListTile(
                          leading: InkWell(
                            onTap: () {
                              //Reverse the value
                              // todo.isDone = !todo.isDone;
                              /*
                            Another magic.
                            This will update Todo isDone with either
                            completed or not
                          */
                              chapterBloc.updateChapter(chapter);
                            },
                            child: Container(
                              //decoration: BoxDecoration(),
                              child: Padding(
                                padding: const EdgeInsets.all(15.0),
                                // child: todo.isDone
                                //     ? Icon(
                                //         Icons.done,
                                //         size: 26.0,
                                //         color: Colors.indigoAccent,
                                //       )
                                //     : Icon(
                                //         Icons.check_box_outline_blank,
                                //         size: 26.0,
                                //         color: Colors.tealAccent,
                                //       ),
                              ),
                            ),
                          ),
                          title: Text(
                            chapter.title,
                            style: TextStyle(
                              fontSize: 16.5,
                              fontFamily: 'RobotoMono',
                              fontWeight: FontWeight.w500,
                            ),
                          )),
                    ));
                return dismissibleCard;
              },
            )
          : Container(
              child: Center(
              //this is used whenever there 0 Todo
              //in the data base
              child: noTodoMessageWidget(),
            ));
    } else {
      return Center(
        /*since most of our I/O operations are done
        outside the main thread asynchronously
        we may want to display a loading indicator
        to let the use know the app is currently
        processing*/
        child: loadingData(),
      );
    }
  }

  Widget loadingData() {
    //pull todos again
    chapterBloc.getChapters();
    return Container(
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            CircularProgressIndicator(),
            Text("Loading...",
                style: TextStyle(fontSize: 19, fontWeight: FontWeight.w500))
          ],
        ),
      ),
    );
  }

  Widget noTodoMessageWidget() {
    return Container(
      child: Text(
        "Start adding Todo...",
        style: TextStyle(fontSize: 19, fontWeight: FontWeight.w500),
      ),
    );
  }

  void _showAddTodoSheet(BuildContext context) {
    final _todoDescriptionFormController = TextEditingController();
    showModalBottomSheet(
        context: context,
        builder: (builder) {
          return new Padding(
            padding: EdgeInsets.only(
                bottom: MediaQuery.of(context).viewInsets.bottom),
            child: new Container(
              color: Colors.transparent,
              child: new Container(
                height: 230,
                decoration: new BoxDecoration(
                    color: Colors.white,
                    borderRadius: new BorderRadius.only(
                        topLeft: const Radius.circular(10.0),
                        topRight: const Radius.circular(10.0))),
                child: Padding(
                  padding: EdgeInsets.only(
                      left: 15, top: 25.0, right: 15, bottom: 30),
                  child: ListView(
                    children: <Widget>[
                      Row(
                        mainAxisSize: MainAxisSize.min,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: <Widget>[
                          Expanded(
                            child: TextFormField(
                              controller: _todoDescriptionFormController,
                              textInputAction: TextInputAction.newline,
                              maxLines: 4,
                              style: TextStyle(
                                  fontSize: 21, fontWeight: FontWeight.w400),
                              autofocus: true,
                              decoration: const InputDecoration(
                                  hintText: 'I have to...',
                                  labelText: 'New Todo',
                                  labelStyle: TextStyle(
                                      color: Colors.indigoAccent,
                                      fontWeight: FontWeight.w500)),
                              validator: (String value) {
                                if (value.isEmpty) {
                                  return 'Empty description!';
                                }
                                return value.contains('')
                                    ? 'Do not use the @ char.'
                                    : null;
                              },
                            ),
                          ),
                          Padding(
                            padding: EdgeInsets.only(left: 5, top: 15),
                            child: CircleAvatar(
                              backgroundColor: Colors.indigoAccent,
                              radius: 18,
                              child: IconButton(
                                icon: Icon(
                                  Icons.save,
                                  size: 22,
                                  color: Colors.white,
                                ),
                                onPressed: () {
                                  final newTodo = Chapter(
                                      title: _todoDescriptionFormController
                                          .value.text);
                                  if (newTodo.title.isNotEmpty) {
                                    /*Create new Todo object and make sure
                                    the Todo description is not empty,
                                    because what's the point of saving empty
                                    Todo
                                    */
                                    chapterBloc.addChapter(newTodo);

                                    //dismisses the bottomsheet
                                    Navigator.pop(context);
                                  }
                                },
                              ),
                            ),
                          )
                        ],
                      ),
                    ],
                  ),
                ),
              ),
            ),
          );
        });
  }
}
