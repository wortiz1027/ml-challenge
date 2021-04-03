package co.com.merdadolibre.challenge.services;

import co.com.merdadolibre.challenge.exceptions.MessageNotDecodeException;
import co.com.merdadolibre.challenge.exceptions.MessageNullException;
import co.com.merdadolibre.challenge.services.contracts.IDecode;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DecodeMessage implements IDecode {

    @Override
    public String getMessage(List<String[]> messages) {
        String[] base   = identifyBase(messages);
        int index 		= messages.indexOf(base);

        if (base == null) throw new MessageNullException("Getting error reading message structure");

        int gap 		= getGap(base);
        int[] positions = identifyPositionEmpty(gap, base);

        complete(index, base, messages, positions);

        return build(base);
    }

    public String build(String[] base) {
        List<String> list = Arrays.asList(base);
        return String.join(" ", list);
    }

    public void complete(int index, String[] base, List<String[]> messages, int[] positions) {
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

    public int[] identifyPositionEmpty(int gap, String[] base) {
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

    public int getGap(String[] base) {
        int gap = 0;

        for (String row : base)
            if (row.equalsIgnoreCase("")) gap++;

        return gap;
    }

    public String[] identifyBase(List<String[]> messages) {
        if (messages.size() == 0) throw new MessageNotDecodeException("Does no possible read the messages");

        for (String[] row : messages)
            if (!row[0].isEmpty()) return row;

        return null;
    }

}