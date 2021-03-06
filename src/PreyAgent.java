public class PreyAgent extends Agent {

    static double p_reproduce = 0.07;
    static int delai_de_famine = 34;
    boolean _alive;
    int it_non_mange;
    int dir;

    public PreyAgent(int __x, int __y, World __w) {
        super(__x, __y, __w);

        _redValue = 0;
        _greenValue = 128;
        _blueValue = 255;

        _alive = true;
        dir = -1;
    }

    public void reset_mange() {
        it_non_mange = 0;
    }

    public boolean isAlive() {
        return _alive;
    }

    /*
     * 0:nord;1:est;2:sud;3:ouest
     */
    public void setDirection(int d) {
        if (dir == -1) {
            dir = d;
            return;
        }
        if (Math.random() < 0.5) dir = d;
    }

    public void step() {
        // met a jour l'agent

        // ... A COMPLETER
        // mange
        it_non_mange++;
        if (it_non_mange > delai_de_famine) {
            _alive = false;
            return;
        }
        // reproduire
        if (Math.random() < p_reproduce) {
            _world.reproduire(new PreyAgent(_x, _y, _world));
        }

        int[] cellColor = _world.getCellState(_x, _y);

        cellColor[redId] = 205;
        cellColor[greenId] = 255;
        cellColor[blueId] = 255;

        _world.setCellState(_x, _y, cellColor);

        if (Math.random() > 0.5) // au hasard
            _orient = (_orient + 1) % 4;
        else
            _orient = (_orient - 1 + 4) % 4;
        if (dir != -1) _orient = dir;
        dir = -1;


        // met a jour: la position de l'agent (depend de l'orientation)
        switch (_orient) {
            // nord
            case 0 -> _y = (_y - 1 + _world.getHeight()) % _world.getHeight();
            // est
            case 1 -> _x = (_x + 1 + _world.getWidth()) % _world.getWidth();
            // sud
            case 2 -> _y = (_y + 1 + _world.getHeight()) % _world.getHeight();
            // ouest
            case 3 -> _x = (_x - 1 + _world.getWidth()) % _world.getWidth();
        }
    }

}
