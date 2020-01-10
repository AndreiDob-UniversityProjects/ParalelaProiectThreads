public class Convolution implements Runnable {
    Image input;
    Image output;
    int row;
    int column;

    public Convolution(Image input, Image output, int row, int column) {
        this.input = input;
        this.output = output;
        this.row = row;
        this.column = column;
    }

    @Override
    public void run() {
//        int sum = input.matrix[column - 1][row - 1] + input.matrix[column - 1][row + 1] + input.matrix[column + 1][row - 1] + input.matrix[column + 1][row + 1];
//        sum+=4*(input.matrix[column-1][row]+input.matrix[column+1][row]+input.matrix[column][row-1]+input.matrix[column][row+1]);
//        sum+=8* input.matrix[column][row];
//        output.matrix[column][row]=sum/28;

        int gx = -1 * (input.matrix[column - 1][row - 1] + input.matrix[column - 1][row + 1]);
        gx += 1 * (input.matrix[column + 1][row - 1] + input.matrix[column + 1][row + 1]);
        gx += -2 * input.matrix[column-1][row];
        gx += 2 * input.matrix[column+1][row];

        int gy = -1 * (input.matrix[column - 1][row - 1] + input.matrix[column + 1][row - 1]);
        gy += 1 * (input.matrix[column - 1][row + 1] + input.matrix[column + 1][row + 1]);
        gy += -2 * input.matrix[column][row-1];
        gy += 2 * input.matrix[column][row+1];


        output.matrix[column][row] = (int) Math.sqrt(gx^2+gy^2);

    }
}
