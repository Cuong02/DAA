import numpy as np
import time
import matplotlib.pyplot as plt

def generate_matrix(n):
    # Tạo ngẫu nhiên ma trận kề với n đỉnh
    matrix = np.random.randint(10, 100, size=(n, n))
    np.fill_diagonal(matrix, 0) # Đặt giá trị trên đường chéo chính bằng 0
    matrix = np.triu(matrix, k=1)
    matrix += matrix.T
    return matrix

def greedy_algorithm(matrix):
    # Tìm kiếm đường đi ngắn nhất sử dụng thuật toán tham lam
    n = matrix.shape[0]
    visited = [0] * n
    path = [0]
    visited[0] = 1
    while len(path) < n:
        current = path[-1]
        next_node = None
        for i in range(n):
            if not visited[i]:
                if next_node is None or matrix[current][i] < matrix[current][next_node]:
                    next_node = i
        path.append(next_node)
        visited[next_node] = 1
    path.append(path[0])
    return path

if __name__ == '__main__':
    n_values = [10, 10**3,10**4 ] # Các giá trị n (số đỉnh) cần kiểm tra
    times = [] # Lưu trữ thời gian chạy của mỗi bài toán
    for n in n_values:
        matrix = generate_matrix(n)
        start_time = time.time()
        path = greedy_algorithm(matrix)
        end_time = time.time()
        times.append(end_time - start_time)
        #print(f"n = {n}, path = {path}, time = {times[-1]} seconds")
    # Vẽ đồ thị đo thời gian chạy
    plt.plot(n_values, times)
    plt.xlabel('Number of vertices')
    plt.ylabel('Time (seconds)')
    plt.title('Running time of TSP with greedy algorithm')
    plt.show()

