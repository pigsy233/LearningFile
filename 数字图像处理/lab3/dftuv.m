function [ U,V ] = dftuv( M, N )
%DFTUV 实现频域滤波器的网格函数
%   Detailed explanation goes here
u = 0:(M - 1);
v = 0:(N - 1);
idx = find(u > M/2); %找大于M/2的数据
u(idx) = u(idx) - M; %将大于M/2的数据减去M
idy = find(v > N/2);
v(idy) = v(idy) - N;
[V, U] = meshgrid(v, u);      

end
