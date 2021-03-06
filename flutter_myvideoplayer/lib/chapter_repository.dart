import 'package:flutter_myvideoplayer/chapter.dart';
import 'package:flutter_myvideoplayer/chapter_dao.dart';

class ChapterRepository {
  final chapterDao = ChapterDao();

  Future getAllChapters({String query}) => chapterDao.getChapter(query: query);

  Future insertChapter(Chapter chapter) => chapterDao.createChapter(chapter);

  Future updateChapter(Chapter chapter) => chapterDao.updateChapter(chapter);

  Future deleteChapterBychapterNo(String chapterNo) =>
      chapterDao.deleteChapter(chapterNo);

  //We are not going to use this in the demo
  Future deleteAllChapters() => chapterDao.deleteAllChapters();
}
