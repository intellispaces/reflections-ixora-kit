package tech.intellispaces.ixora.rdb.query;

import java.util.ArrayList;
import java.util.List;

import tech.intellispaces.ixora.data.collection.Lists;
import tech.intellispaces.reflections.annotation.Guide;

@Guide
public class CastStringToParameterizedNamedQueryGuideImpl implements CastStringToParameterizedNamedQueryGuide {

  @Override
  public ParameterizedNamedQueryHandle castStringToParameterizedNamedQuery(String query) {
    char[] originQuery = query.toCharArray();
    char[] blindQuery = new char[query.length()];
    List<String> paramNames = new ArrayList<>();
    int index1 = 0, index2 = 0;
    while (index1 < originQuery.length) {
      char ch = originQuery[index1++];
      if (ch == ':') {
        blindQuery[index2++] = '?';
        int ind = index1;
        while (index1 < originQuery.length && Character.isLetterOrDigit(originQuery[index1])) {
          index1++;
        }
        String paramName = query.substring(ind, index1);
        paramNames.add(paramName);
      } else {
        blindQuery[index2++] = ch;
      }
    }
    return ParameterizedNamedQueries.create(
        new String(blindQuery, 0, index2),
        Lists.handleOf(paramNames, String.class)
    );
  }
}
