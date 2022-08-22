/**
 * The enum Timesig. Represents the possible time signatures of bagpipe tunes
 */
public enum Timesig {
    TWOTWO{
        @Override
        public String toString() {
            return "2/2";
        }
    }
    , TWOFOUR{
        @Override
        public String toString() {
            return "2/4";
        }
    }
    , THREEFOUR{
        @Override
        public String toString() {
            return "3/4";
        }
    }
    , FOURFOUR{
        @Override
        public String toString() {
            return "4/4";
        }
    }
    , SIXEIGHT{
        @Override
        public String toString() {
            return "6/8";
        }
    }
    , NINEEIGHT{
        @Override
        public String toString() {
            return "9/8";
        }
    }
    , TWELVEEIGHT{
        @Override
        public String toString() {
            return "12/8";
        }
    }

}
