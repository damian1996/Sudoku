public class Results {
    public static int[][] sudoku =
            {{1, 3, 4, -1, -1, 5, -1, 2, -1},
                    {-1, 5, -1, -1, -1, 3, -1, 4, -1},
                    {-1, 6, -1, 7, 9, -1, 1, 3, 5},
                    {3, 8, 5, -1, -1, -1, 9, -1, 4},
                    {4, -1, 6, -1, -1, -1, 2, -1, 3},
                    {2, -1, 9, -1, -1, -1, 5, 6, 7},
                    {-1, -1, -1, 3, 1, -1, 6, -1, 2},
                    {6, -1, 3, 5, 8, -1, 4, -1, 1},
                    {5, -1, 1, -1, -1, 7, -1, -1, -1}};

    public static boolean[][] masks =
            {{true, true, true, false, false, true, false, true, false},
                    {false, true, false, false, false, true, false, true, false},
                    {false, true, false, true, true, false, true, true, true},
                    {true, true, true, false, false, false, true, false, true},
                    {true, false, true, false, false, false, true, false, true},
                    {true, false, true, false, false, false, true, true, true},
                    {false, false, false, true, true, false, true, false, true},
                    {true, false, true, true, true, false, true, false, true},
                    {true, false, true, false, false, true, false, false, false}};

    public static boolean[][] czyWstawione =
            {{true, true, true, false, false, true, false, true, false},
                    {false, true, false, false, false, true, false, true, false},
                    {false, true, false, true, true, false, true, true, true},
                    {true, true, true, false, false, false, true, false, true},
                    {true, false, true, false, false, false, true, false, true},
                    {true, false, true, false, false, false, true, true, true},
                    {false, false, false, true, true, false, true, false, true},
                    {true, false, true, true, true, false, true, false, true},
                    {true, false, true, false, false, true, false, false, false}};

    public static int[][] areas =
            {{1, 1, 1, 2, 2, 2, 3, 3, 3},
                    {1, 1, 1, 2, 2, 2, 3, 3, 3},
                    {1, 1, 1, 2, 2, 2, 3, 3, 3},
                    {4, 4, 4, 5, 5, 5, 6, 6, 6},
                    {4, 4, 4, 5, 5, 5, 6, 6, 6},
                    {4, 4, 4, 5, 5, 5, 6, 6, 6},
                    {7, 7, 7, 8, 8, 8, 9, 9, 9},
                    {7, 7, 7, 8, 8, 8, 9, 9, 9},
                    {7, 7, 7, 8, 8, 8, 9, 9, 9}};

    public static int[][] sudoku2 =
            {{-1, -1, -1, -1, -1, 6},
                    {-1, -1, -1, -1, 5, -1},
                    {-1, -1, -1, 4, -1, -1},
                    {-1, -1, 3, -1, -1, -1},
                    {-1, 2, -1, -1, -1, -1},
                    {1, -1, -1, -1, -1, -1}};

    public static boolean[][] masks2 =
            {{false, false, false, false, false, true},
                    {false, false, false, false, true, false},
                    {false, false, false, true, false, false},
                    {false, false, true, false, false, false},
                    {false, true, false, false, false, false},
                    {true, false, false, false, false, false}};

    public static boolean[][] czyWstawione2 =
            {{false, false, false, false, false, true},
                    {false, false, false, false, true, false},
                    {false, false, false, true, false, false},
                    {false, false, true, false, false, false},
                    {false, true, false, false, false, false},
                    {true, false, false, false, false, false}};

    public static int[][] areas2 =
            {{1, 1, 1, 1, 1, 2},
                    {1, 2, 2, 2, 2, 2},
                    {3, 4, 4, 4, 4, 4},
                    {3, 5, 5, 5, 5, 4},
                    {3, 3, 6, 5, 5, 6},
                    {3, 3, 6, 6, 6, 6}};

    public static int[][] sudoku3 =
            {{4, -1, -1, -1, -1, -1, -1, -1, -1, 9, -1, -1, -1, -1, -1, 10},
                    {1, -1, -1, -1, 10, -1, 15, -1, -1, -1, 2, -1, 7, -1, -1, -1},
                    {-1, 12, 16, -1, 2, -1, 3, 9, 5, -1, 7, -1, 10, -1, -1, -1},
                    {-1, -1, 1, -1, 3, 8, -1, 11, -1, 15, -1, -1, 4, -1, -1, -1},
                    {10, 11, -1, -1, 14, -1, 12, -1, 8, -1, -1, -1, -1, -1, -1, -1},
                    {-1, -1, 5, -1, 7, -1, -1, -1, -1, -1, -1, 3, -1, -1, -1, 16},
                    {2, -1, 15, -1, -1, 5, -1, 6, -1, 3, 12, 14, 1, -1, -1, -1},
                    {3, -1, -1, -1, -1, -1, -1, 7, -1, -1, -1, 5, 13, 6, -1, 4},
                    {-1, -1, 8, -1, 15, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1, 1},
                    {9, -1, 14, 5, -1, 10, -1, -1, -1, -1, -1, 4, 2, -1, 6, -1},
                    {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 16, 10, -1, -1, 15, -1},
                    {-1, -1, 2, -1, 13, -1, 16, -1, 6, -1, -1, -1, 9, -1, -1, 11},
                    {-1, 13, -1, 10, -1, 9, -1, 12, -1, -1, -1, -1, -1, -1, -1, 8},
                    {-1, -1, -1, 14, -1, 2, -1, -1, 4, -1, -1, -1, -1, 16, 1, 7},
                    {-1, 14, 7, 6, -1, -1, 5, 10, -1, -1, -1, 9, -1, 8, 11, -1},
                    {-1, -1, 11, -1, -1, -1, -1, -1, -1, -1, -1, 15, -1, -1, -1, -1}};

    public static int[][] areas3 =
            {{1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {3, 3, 1, 3, 1, 1, 1, 1, 2, 2, 6, 2, 2, 2, 4, 2},
                    {3, 3, 3, 3, 1, 1, 6, 6, 6, 2, 6, 6, 6, 6, 4, 4},
                    {3, 7, 7, 3, 3, 1, 1, 6, 6, 6, 6, 6, 6, 4, 4, 4},
                    {3, 7, 7, 7, 7, 7, 14, 6, 5, 5, 5, 6, 4, 4, 4, 5},
                    {3, 3, 7, 7, 7, 14, 14, 14, 14, 14, 5, 4, 4, 4, 4, 5},
                    {8, 3, 3, 3, 7, 14, 14, 14, 14, 14, 5, 5, 4, 4, 4, 5},
                    {8, 8, 8, 9, 7, 14, 15, 14, 14, 13, 5, 5, 5, 5, 5, 5},
                    {8, 8, 8, 9, 7, 7, 15, 14, 14, 13, 13, 13, 13, 13, 13, 5},
                    {8, 9, 9, 9, 7, 7, 15, 15, 15, 15, 13, 13, 13, 13, 13, 12},
                    {8, 8, 9, 9, 16, 16, 16, 15, 16, 15, 13, 15, 13, 13, 13, 12},
                    {8, 8, 9, 9, 9, 9, 16, 16, 16, 15, 15, 15, 12, 12, 12, 12},
                    {8, 10, 9, 9, 9, 9, 16, 16, 16, 16, 15, 12, 12, 12, 12, 12},
                    {8, 10, 10, 10, 9, 16, 16, 16, 11, 16, 15, 12, 12, 11, 12, 12},
                    {8, 8, 10, 10, 10, 16, 10, 11, 11, 11, 15, 15, 11, 11, 11, 12},
                    {10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11}};

    public static boolean[][] czyWstawione3 =
            {{true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, true},
                    {true, false, false, false, true, false, true, false, false, false, true, false, true, false, false, false},
                    {false, true, true, false, true, false, true, true, true, false, true, false, true, false, false, false},
                    {false, false, true, false, true, true, false, true, false, true, false, false, true, false, false, false},
                    {true, true, false, false, true, false, true, false, true, false, false, false, false, false, false, false},
                    {false, false, true, false, true, false, false, false, false, false, false, true, false, false, false, true},
                    {true, false, true, false, false, true, false, true, false, true, true, true, true, false, false, false},
                    {true, false, false, false, false, false, false, true, false, false, false, true, true, true, false, true},
                    {false, false, true, false, true, false, false, false, true, false, false, false, false, false, false, true},
                    {true, false, true, true, false, true, false, false, false, false, false, true, true, false, true, false},
                    {false, false, false, false, false, false, false, false, false, false, true, true, false, false, true, false},
                    {false, false, true, false, true, false, true, false, true, false, false, false, true, false, false, true},
                    {false, true, false, true, false, true, false, true, false, false, false, false, false, false, false, true},
                    {false, false, false, true, false, true, false, false, true, false, false, false, false, true, true, true},
                    {false, true, true, true, false, false, true, true, false, false, false, true, false, true, true, false},
                    {false, false, true, false, false, false, false, false, false, false, false, true, false, false, false, false}};

    public static boolean[][] masks3 =
            {{true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, true},
                    {true, false, false, false, true, false, true, false, false, false, true, false, true, false, false, false},
                    {false, true, true, false, true, false, true, true, true, false, true, false, true, false, false, false},
                    {false, false, true, false, true, true, false, true, false, true, false, false, true, false, false, false},
                    {true, true, false, false, true, false, true, false, true, false, false, false, false, false, false, false},
                    {false, false, true, false, true, false, false, false, false, false, false, true, false, false, false, true},
                    {true, false, true, false, false, true, false, true, false, true, true, true, true, false, false, false},
                    {true, false, false, false, false, false, false, true, false, false, false, true, true, true, false, true},
                    {false, false, true, false, true, false, false, false, true, false, false, false, false, false, false, true},
                    {true, false, true, true, false, true, false, false, false, false, false, true, true, false, true, false},
                    {false, false, false, false, false, false, false, false, false, false, true, true, false, false, true, false},
                    {false, false, true, false, true, false, true, false, true, false, false, false, true, false, false, true},
                    {false, true, false, true, false, true, false, true, false, false, false, false, false, false, false, true},
                    {false, false, false, true, false, true, false, false, true, false, false, false, false, true, true, true},
                    {false, true, true, true, false, false, true, true, false, false, false, true, false, true, true, false},
                    {false, false, true, false, false, false, false, false, false, false, false, true, false, false, false, false}};

}