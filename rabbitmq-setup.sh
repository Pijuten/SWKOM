#!/bin/bash
set -e

# Start RabbitMQ server in the background
docker-entrypoint.sh rabbitmq-server &

# Wait for RabbitMQ to fully start
sleep 15

# Declare queues
rabbitmqadmin declare queue name=result_queue durable=true
rabbitmqadmin declare queue name=ocr_queue durable=true

# Wait to keep container alive
wait
