package vobis.example.com.gamification;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import vobis.example.com.gamification.me2minigame.TileDesc;
import vobis.example.com.gamification.me2minigame.gameconfig.CodesSelector;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.mockito.Mockito.*;

public class MEMinigameTest {

    @Mock
    CodesSelector codesSelector;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testSelector() throws TileDesc.WrongTileException{
        TileDesc tileDesc = new TileDesc(false, false, 1, codesSelector);
        exception.expect(TileDesc.WrongTileException.class);
        tileDesc.accept();
        verify(codesSelector, times(2)).getCurrentResId();
    }

    @Test
    public void testNoException()throws TileDesc.WrongTileException{
        final TileDesc tileDesc = new TileDesc(false,false,1,codesSelector);
        when(codesSelector.getCurrentResId()).thenReturn(1);
        tileDesc.accept();
    }

    @Captor
    private ArgumentCaptor<List<String>> captor;


    @Test
    public final void shouldContainCertainListItem() {
        List<String> asList = Arrays.asList("someElement_test", "someElement");
        final List<String> mockedList = mock(List.class);
        mockedList.addAll(asList);

        verify(mockedList).addAll(captor.capture());
        final List<String> capturedArgument = captor.getValue();
        assertThat(capturedArgument, hasItem("someElement"));
    }

}
