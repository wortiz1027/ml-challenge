package co.com.merdadolibre.challenge.services;

import java.util.Arrays;
import java.util.List;

public class DecodeMessage {

    public String createMessage(List<String[]> messages) {
        String[] base   = identifyBase(messages);
        int index 		= messages.indexOf(base);
        assert base != null;
        int gap 		= getGap(base);
        int[] positions = identifyPositionEmpty(gap, base);

        complete(index, base, messages, positions);

        return build(base);
    }

    private String build(String[] base) {
        List<String> list = Arrays.asList(base);
        return String.join(" ", list);
    }

    private void complete(int index, String[] base, List<String[]> messages, int[] positions) {
        for (String[] row : messages) {
            if (messages.indexOf(row) != index) {
                for (int i = 0; i < row.length; i++) {
                    if (!row[i].isEmpty()) {
                        for (int position : positions) {
                            if (i == position) {
                                base[position] = row[i];
                            }
                        }
                    }
                }
            }
        }
    }

    private int[] identifyPositionEmpty(int gap, String[] base) {
        int pos = 0;
        int index = 0;
        int[] positions = new int[gap];

        for (String row : base) {
            if (row.isEmpty()) {
                positions[pos] = index;
                pos++;
            }
            index++;
        }

        return positions;
    }

    private int getGap(String[] base) {
        int gap = 0;

        for (String row : base)
            if (row.equalsIgnoreCase("")) gap++;

        return gap;
    }

    private String[] identifyBase(List<String[]> x) {
        for (String[] row : x)
            if (!row[0].isEmpty())
                return row;

        return null;
    }

}